package co.edu.usbcali.clum.service;

import co.edu.usbcali.clum.domain.*;
import co.edu.usbcali.clum.dto.CanalizacionDTO;
import co.edu.usbcali.clum.exception.*;
import co.edu.usbcali.clum.mapper.CanalizacionMapper;
import co.edu.usbcali.clum.repository.*;
import co.edu.usbcali.clum.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class CanalizacionServiceImpl implements CanalizacionService {
    private static final Logger log = LoggerFactory.getLogger(CanalizacionServiceImpl.class);

    /**
     * Repository injected by Spring that manages Canalizacion entities
     *
     */
    @Autowired
    private CanalizacionRepository canalizacionRepository;
    @Autowired
    private CanalizacionMapper canalizacionMapper;
    @Autowired
    private Validator validator;

    /**
    * Service injected by Spring that manages Estado entities
    *
    */
    @Autowired
    EstadoService serviceEstado1;

    /**
    * Service injected by Spring that manages Lampara entities
    *
    */
    @Autowired
    LamparaService serviceLampara2;

    /**
    * Service injected by Spring that manages TipoZona entities
    *
    */
    @Autowired
    TipoZonaService serviceTipoZona3;

    public void validateCanalizacion(Canalizacion canalizacion)
        throws Exception {
        try {
            Set<ConstraintViolation<Canalizacion>> constraintViolations = validator.validate(canalizacion);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Canalizacion> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Canalizacion> getCanalizacion() throws Exception {
        log.debug("finding all Canalizacion instances");

        List<Canalizacion> list = new ArrayList<Canalizacion>();

        try {
            list = canalizacionRepository.findAll();
        } catch (Exception e) {
            log.error("finding all Canalizacion failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Canalizacion");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveCanalizacion(Canalizacion entity) throws Exception {
        log.debug("saving Canalizacion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Canalizacion");
            }

            validateCanalizacion(entity);

            if (getCanalizacion(entity.getCanalizacionId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            canalizacionRepository.save(entity);
            log.debug("save Canalizacion successful");
        } catch (Exception e) {
            log.error("save Canalizacion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCanalizacion(Canalizacion entity)
        throws Exception {
        log.debug("deleting Canalizacion instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Canalizacion");
        }

        if (entity.getCanalizacionId() == null) {
            throw new ZMessManager().new EmptyFieldException("canalizacionId");
        }

        try {
            canalizacionRepository.deleteById(entity.getCanalizacionId());
            log.debug("delete Canalizacion successful");
        } catch (Exception e) {
            log.error("delete Canalizacion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateCanalizacion(Canalizacion entity)
        throws Exception {
        log.debug("updating Canalizacion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Canalizacion");
            }

            validateCanalizacion(entity);

            canalizacionRepository.update(entity);

            log.debug("update Canalizacion successful");
        } catch (Exception e) {
            log.error("update Canalizacion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<CanalizacionDTO> getDataCanalizacion()
        throws Exception {
        try {
            List<Canalizacion> canalizacion = canalizacionRepository.findAll();

            List<CanalizacionDTO> canalizacionDTO = new ArrayList<CanalizacionDTO>();

            for (Canalizacion canalizacionTmp : canalizacion) {
                CanalizacionDTO canalizacionDTO2 = canalizacionMapper.canalizacionToCanalizacionDTO(canalizacionTmp);
                canalizacionDTO.add(canalizacionDTO2);
            }

            return canalizacionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Canalizacion getCanalizacion(Integer canalizacionId)
        throws Exception {
        log.debug("getting Canalizacion instance");

        Canalizacion entity = null;

        try {
            if (canalizacionRepository.findById(canalizacionId).isPresent()) {
                entity = canalizacionRepository.findById(canalizacionId).get();
            }
        } catch (Exception e) {
            log.error("get Canalizacion failed", e);
            throw new ZMessManager().new FindingException("Canalizacion");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Canalizacion> findPageCanalizacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Canalizacion> entity = null;

        try {
            entity = canalizacionRepository.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Canalizacion Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberCanalizacion() throws Exception {
        Long entity = null;

        try {
            entity = canalizacionRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Canalizacion Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Canalizacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Canalizacion> list = new ArrayList<Canalizacion>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between " + value +
                        " and " + value2 + ")")
                        : (tempWhere + " AND (model." + variable + " between " +
                        value + " and " + value2 + ")");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = canalizacionRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
