package org.arai.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.arai.Persistence.Entities.Auditoria;
import org.arai.Persistence.Repositories.AuditoriaRepository;
import org.arai.Security.RequestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

@Service
public class AuditoriaService {

    private Logger logger = LoggerFactory.getLogger(AuditoriaService.class);
    private final AuditoriaRepository auditoriaRepository;
    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        return attrs != null ? attrs.getRequest() : null;
    }

    public AuditoriaService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    private void insertAuditoria(Auditoria auditoria) {
       Integer lasid = auditoriaRepository.insertAuditoria(auditoria);
       logger.info("Auditoria registrado: {}", lasid);
    }

    public List<Auditoria> getAllAuditorias() {
        List<Auditoria> auditorias = auditoriaRepository.findAllAuditorias();
        logger.info("Total de auditorias encontradas: {}", auditorias.size());
        return auditorias;
    }


    //operations for specific auditoria queries can be added here

    public void loginAttempt() {
        var auditoria = new Auditoria();
        auditoria.setOperacion("LOGIN_ATTEMPT");
        auditoria.setUser_id(-1); // -1 for anonymous user
        auditoria.setTimestamp(new Date());
        auditoria.setNombre_tabla("N/A");
        auditoria.setFila_tabla(-1); // -1 for no specific row
        auditoria.setNuevo_datos("{}");
        auditoria.setViejos_datos("{}");
        auditoria.setIp_address(RequestInfo.getClientIp(getCurrentHttpRequest()));
        auditoria.setUser_agent(RequestInfo.getUserAgent(getCurrentHttpRequest()));
        this.insertAuditoria(auditoria);
    }

    public void LoginSuccess(Integer userId) {
        var auditoria = new Auditoria();
        auditoria.setOperacion("LOGIN_SUCCESS");
        auditoria.setUser_id(userId);
        auditoria.setTimestamp(new Date());
        auditoria.setNombre_tabla("N/A");
        auditoria.setFila_tabla(-1); // -1 for no specific row
        auditoria.setNuevo_datos("{}");
        auditoria.setViejos_datos("{}");
        auditoria.setIp_address(RequestInfo.getClientIp(getCurrentHttpRequest()));
        auditoria.setUser_agent(RequestInfo.getUserAgent(getCurrentHttpRequest()));
        this.insertAuditoria(auditoria);
    }


    public void newAuditoriaLogChange(
            String operacion,
            String nombreTabla,
            Integer filaTabla,
            String nuevoDatos,
            String viejosDatos
    ) {
        var auditoria = new Auditoria();
        auditoria.setOperacion(operacion);
        auditoria.setUser_id(Integer.valueOf(filaTabla));
        auditoria.setTimestamp(new Date());
        auditoria.setNombre_tabla(nombreTabla);
        auditoria.setFila_tabla(filaTabla);
        auditoria.setNuevo_datos(nuevoDatos);
        auditoria.setViejos_datos(viejosDatos);
        auditoria.setIp_address(RequestInfo.getClientIp(getCurrentHttpRequest()));
        auditoria.setUser_agent(RequestInfo.getUserAgent(getCurrentHttpRequest()));
        this.insertAuditoria(auditoria);
    }

    public void newAuditoriaLogAppend(
            String operacion,
            String nombreeTabla,
            String nuevosDatosJson,
            Integer filaTabla
    ){

        var auditoria = new Auditoria();
        auditoria.setOperacion(operacion);
        auditoria.setUser_id(Integer.valueOf(filaTabla));
        auditoria.setTimestamp(new Date());
        auditoria.setNombre_tabla(nombreeTabla);
        auditoria.setFila_tabla(filaTabla);
        auditoria.setNuevo_datos(nuevosDatosJson);
        auditoria.setViejos_datos("{}");
        auditoria.setIp_address(RequestInfo.getClientIp(getCurrentHttpRequest()));
        auditoria.setUser_agent(RequestInfo.getUserAgent(getCurrentHttpRequest()));
        this.insertAuditoria(auditoria);
    }


}
