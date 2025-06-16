package org.arai.DConfiguration;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
@Order(1)
public class DatabaseStartupValidator implements SmartLifecycle {
    private boolean isRunning = false;
    private Logger logger = LoggerFactory.getLogger(DatabaseStartupValidator.class);
    private final DataSource dataSource;

    public DatabaseStartupValidator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void start() {
        if (!isRunning) {
            try {
                logger.info("🔍 Iniciando verificación de conexión a la base de datos...");

                if (dataSource == null) {
                    throw new IllegalStateException("DataSource no ha sido inyectado correctamente");
                }

                try (Connection connection = dataSource.getConnection()) {
                    // 3. Verificación adicional para PostgreSQL
                    try (Statement stmt = connection.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT 1")) {

                        if (rs.next()) {
                            logger.info("✅ Conexión a la base de datos verificada exitosamente");
                        } else {
                            throw new IllegalStateException("La prueba de conexión no devolvió resultados");
                        }
                    }
                }

                if (dataSource instanceof HikariDataSource) {
                    HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
                    logger.info("📊 Métricas del pool:");
                    logger.info("   - Nombre: {}", hikariDataSource.getPoolName());
                    logger.info("   - Estado: {}", hikariDataSource.isRunning() ? "ACTIVO" : "INACTIVO");

                    if (hikariDataSource.getHikariPoolMXBean() != null) {
                        logger.info("   - Conexiones activas: {}",
                                hikariDataSource.getHikariPoolMXBean().getActiveConnections());
                    }
                }

                isRunning = true;
            } catch (Exception e) {
                logger.error("❌ FALLA CRÍTICA: No se pudo establecer conexión con la base de datos", e);
                logger.error("🛑 APLICACIÓN SERÁ DETENIDA - Verifique:");
                logger.error("   1. Credenciales de la base de datos");
                logger.error("   2. Conexión de red al servidor de BD");
                logger.error("   3. Que la base de datos esté corriendo");
                System.exit(1);
            }
        }
    }
    @Override
    public void stop() {
        if (isRunning) {
            System.out.println("🔴 Cerrando validador de base de datos");
            isRunning = false;
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return Integer.MIN_VALUE;
    }
}
