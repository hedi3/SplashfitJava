/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.services;

import shopp.entities.Reclamation;
import java.util.List;

/**
 *
 * @author wided
 */
public interface reclamationinterface {
    public void ajouterReclamation(Reclamation a);
    public List<Reclamation> afficherReclamation();
    public boolean modifierReclamation(Reclamation a);
    public boolean supprimerReclamation(int id);
}
