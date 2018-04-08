/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Artista;
import java.util.List;

/**
 *
 * @author scardonas
 */
public interface IArtista_DAO {
    public boolean setArtista(Artista art);
    public List<Artista> getArtistas();
    public boolean deleteArtista(Artista art);
    public boolean updateArtista(Artista art);
}
