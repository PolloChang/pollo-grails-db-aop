package work.pollochang.pollo.grails.db.aop;

import org.grails.web.util.WebUtils;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
import org.hibernate.internal.EntityManagerMessageLogger;
import org.hibernate.internal.HEMLogging;
import org.hibernate.type.Type;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 自訂攔截器
 */
public class CustomInterceptor extends EmptyInterceptor {

    private static final EntityManagerMessageLogger log = HEMLogging.messageLogger( CustomInterceptor.class );

    private DataSource dataSource;

    public CustomInterceptor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.trace("onDelete");
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        log.trace("onDelete");
    }

    @Override
    public boolean onFlushDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        log.trace("onFlushDirty");
        return false;
    }

    @Override
    public boolean onLoad(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        log.trace("onLoad");
        return false;
    }


    @Override
    public void postFlush(Iterator entities) {
        log.trace("postFlush");
    }

    @Override
    public void preFlush(Iterator entities) {
        log.trace("preFlush");
    }

    @Override
    public Boolean isTransient(Object entity) {
        log.trace("isTransient");
        return null;
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
        log.trace("instantiate");
        return null;
    }

    @Override
    public int[] findDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        log.trace("findDirty");
        return null;
    }

    @Override
    public String getEntityName(Object object) {
        log.trace("getEntityName");
        return null;
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        log.trace("getEntity");
        return null;
    }

    /**
     * 執行順序: 1
     * @param tx The Hibernate transaction facade object
     */
    @Override
    public void afterTransactionBegin(Transaction tx) {


//        String sessionId = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSessionId();
//        log.trace("sessionId = "+sessionId);

        HttpServletRequest request = WebUtils.retrieveGrailsWebRequest().getRequest();
        if (request != null) {
            // 在这里访问 HTTP 请求对象，例如获取请求参数等
            String parameterValue = request.getParameter("parameterName");
            log.debug("Parameter value from request: " + parameterValue);

            String realIp = request.getRemoteAddr();
            log.debug("user IP = "+realIp);
        }

        // 获取数据库连接
//        Connection connection = null;
        try (Connection connection = dataSource.getConnection()) {
//            connection = dataSource.getConnection();
            // 执行Dummy SQL语句
            executeDummySQL(connection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        log.trace("afterTransactionBegin");
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        log.trace("afterTransactionCompletion");
    }

    /**
     * 2
     * @param tx The Hibernate transaction facade object
     */
    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        log.trace("beforeTransactionCompletion");
    }

    @Override
    public String onPrepareStatement(String sql) {
        log.trace("onPrepareStatement");
        return sql;
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
        log.trace("onCollectionRemove");
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
        log.trace("onCollectionRecreate");
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
        log.trace("onCollectionUpdate");
    }

    /**
     * 查詢虛擬表
     * @param connection
     * @throws SQLException
     */
    private void executeDummySQL(Connection connection) throws SQLException {
        log.trace("executeDummySQL");
        // 在这里执行Dummy SQL语句
        Statement statement = connection.createStatement();
        statement.execute("insert into test2(col1) values(0)");
        statement.close();
    }
}