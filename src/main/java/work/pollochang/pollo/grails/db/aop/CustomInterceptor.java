package work.pollochang.pollo.grails.db.aop;

import org.grails.web.util.WebUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.internal.EntityManagerMessageLogger;
import org.hibernate.internal.HEMLogging;
import org.hibernate.type.Type;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.Serializable;

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

//        String sessionId = org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes().getSessionId();
//        System.out.println("sessionId = "+sessionId);

        HttpServletRequest request = WebUtils.retrieveGrailsWebRequest().getRequest();
        if (request != null) {
            // 在这里访问 HTTP 请求对象，例如获取请求参数等
            String parameterValue = request.getParameter("parameterName");
            System.out.println("Parameter value from request: " + parameterValue);

            String realIp = request.getRemoteAddr();
            System.out.println("user IP = "+realIp);
        }

        // 获取数据库连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            // 执行Dummy SQL语句
            executeDummySQL(connection);
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return super.onSave(entity, id, state, propertyNames, types);
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