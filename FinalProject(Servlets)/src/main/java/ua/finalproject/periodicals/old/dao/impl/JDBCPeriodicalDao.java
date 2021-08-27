package ua.finalproject.periodicals.old.dao.impl;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;
import ua.finalproject.periodicals.old.dao.PeriodicalDao;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.Criteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class JDBCPeriodicalDao implements PeriodicalDao {
    private static final String QUERY_FIND_ALL = "SELECT * FROM periodical";
    private static final String QUERY_FIND_ALL_LIMIT="SELECT * FROM periodical ORDER BY title LIMIT ?,?";
    private static final String QUERY_FIND_TOPICS = "SELECT DISTINCT topic FROM periodical";
    private static final String QUERY_FIND_TOPICS_BY_USER = "SELECT DISTINCT topic FROM periodical p "+
            "INNER JOIN subscription s ON p.id=s.periodical_id WHERE user_id = ?";
    private static final String QUERY_SELECT = "SELECT * FROM periodical ";
    private static final String QUERY_DELETE = "DELETE FROM periodical WHERE id = ?";
    private static final String QUERY_DELETE_BY_TITLE = "DELETE FROM periodical WHERE title LIKE ?";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM periodical WHERE id=?";
    private static final String QUERY_INSERT = "INSERT INTO periodical(price, title, topic) VALUES (?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE periodical " +
            " SET price=?, title = ?, topic=? WHERE id = ?";
    private static final Logger logger = Logger.getLogger(JDBCPeriodicalDao.class.getName());

    private static final int PAGE_SIZE = Integer.valueOf(Configurations.getProperty(Constants.PAGE_SIZE));
    private Connection connection;

    public JDBCPeriodicalDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Periodical entity) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT)) {
            prepareStatementForEntity(preparedStatement, entity);
            preparedStatement.execute();
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Optional<Periodical> findById(Long id) {
        Optional<Periodical> optional = Optional.ofNullable(null);
        try (PreparedStatement ps = connection.prepareStatement(QUERY_FIND_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                optional = Optional.of(extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return optional;
    }

    @Override
    public List<Periodical> findAll() {
        List<Periodical> entities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                entities.add(extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return entities;
    }
    @Override
    public List<Periodical> findAll(int number) {
        List<Periodical> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL_LIMIT)) {
            statement.setInt(1, number*PAGE_SIZE);
            statement.setInt(2, PAGE_SIZE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entities.add(extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return entities;
    }


    private Periodical extractFromResultSet(ResultSet rs) throws SQLException {
        Periodical entity = new Periodical();

        entity.setId(rs.getLong("id"));
        entity.setTitle(rs.getString("title"));
        entity.setPrice(rs.getBigDecimal("price"));
        entity.setTopic(rs.getString("topic"));

        return entity;
    }

    public List<String> findTopics() {
        List<String> topics = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(QUERY_FIND_TOPICS);
            while (rs.next()) {
                topics.add(rs.getString(1));
                logger.info(rs.getString(1));
            }
            logger.info("RESULT: "+topics+" size: "+topics.size());
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return topics;
    }

    @Override
    public List<Periodical> findByCriteria(Criteria criteria) {
        List<Periodical> entities = new ArrayList<>();

        String query = buildQueryForCriteria(criteria);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            prepareStatementForFindByCriteria(preparedStatement, criteria);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                entities.add(extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return entities;
    }

    @Override
    public void deleteByTitle(String title) {
        try(PreparedStatement preparedStatement=connection.prepareStatement(QUERY_DELETE_BY_TITLE)){
            preparedStatement.setString(1, "%"+title+"%");
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            logger.severe(ex.getMessage());
        }
    }

    @Override
    public List<String> findAllTopicsByUser(Long userId) {
        List<String> topics= new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_TOPICS_BY_USER)){
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                topics.add(rs.getString(1));
            }
        }catch(SQLException ex){
            logger.severe(ex.getMessage());
        }
        return topics;
    }

    private String buildQueryForCriteria(Criteria criteria) {
        StringBuilder sb = new StringBuilder(QUERY_SELECT);
        if (criteria.getUserId().isPresent()) {
            sb.append(" INNER JOIN subscription ON periodical.id=subscription.periodical_id ");
        }
        sb.append(" WHERE TITLE LIKE ? ");
        if (!criteria.getTopics().isEmpty()) {
            sb.append("AND topic IN ");
            sb.append(tupleWithQuestionMarks(criteria.getTopics().size()));
        }
        if (criteria.getUserId().isPresent()) {
            sb.append(" AND user_id = ? ");
        }
        sb.append(" ORDER BY " + criteria.getFieldForSort() + " LIMIT ?,?");
        return sb.toString();
    }


    private String tupleWithQuestionMarks(int number) {
        StringBuilder sb = new StringBuilder(" (");
        IntStream.range(0, number)
                .forEach(i -> sb.append(i == 0 ? "? " : ", ? "));
        return sb.append(") ").toString();
    }

    private void prepareStatementForFindByCriteria(PreparedStatement preparedStatement, Criteria criteria) throws SQLException {
        int i = 1;
        preparedStatement.setString(i++, "%" + criteria.getTitle() + "%");
        for (String s : criteria.getTopics()) {
            preparedStatement.setString(i++, s);
        }
        if (criteria.getUserId().isPresent()) {
            preparedStatement.setLong(i++, criteria.getUserId().get());
        }
        // Page enum starts from 0.
        int offset = PAGE_SIZE * criteria.getNumber();
        preparedStatement.setInt(i++, offset);
        preparedStatement.setInt(i++, PAGE_SIZE);
    }


    @Override
    public void update(Periodical entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE)) {
            prepareStatementForUpdate(ps, entity);
            ps.execute();

        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
            throw ex;
        }
    }

    private void prepareStatementForUpdate(PreparedStatement ps, Periodical entity) throws SQLException {
        prepareStatementForEntity(ps, entity);
        ps.setLong(4, entity.getId());
    }

    private void prepareStatementForEntity(PreparedStatement ps, Periodical entity) throws SQLException {
        ps.setBigDecimal(1, entity.getPrice());
        ps.setString(2, entity.getTitle());
        ps.setString(3, entity.getTopic());
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_DELETE)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
