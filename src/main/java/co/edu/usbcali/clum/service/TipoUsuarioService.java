package co.edu.usbcali.clum.service;

import co.edu.usbcali.clum.domain.TipoUsuario;
import co.edu.usbcali.clum.dto.TipoUsuarioDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface TipoUsuarioService {
    public List<TipoUsuario> getTipoUsuario() throws Exception;

    /**
         * Save an new TipoUsuario entity
         */
    public void saveTipoUsuario(TipoUsuario entity) throws Exception;

    /**
         * Delete an existing TipoUsuario entity
         *
         */
    public void deleteTipoUsuario(TipoUsuario entity) throws Exception;

    /**
        * Update an existing TipoUsuario entity
        *
        */
    public void updateTipoUsuario(TipoUsuario entity) throws Exception;

    /**
         * Load an existing TipoUsuario entity
         *
         */
    public TipoUsuario getTipoUsuario(Integer tipoUsuarioId)
        throws Exception;

    public List<TipoUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoUsuario> findPageTipoUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoUsuario() throws Exception;

    public List<TipoUsuarioDTO> getDataTipoUsuario() throws Exception;

    public void validateTipoUsuario(TipoUsuario tipoUsuario)
        throws Exception;
}
