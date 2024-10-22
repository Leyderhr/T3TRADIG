package logic.useful;

import logic.DAO.DAOAmbito;
import logic.DAO.DAODimension;
import logic.DAO.DAOPerspectiva;
import logic.DAO.DAOPregunta;
import logic.Entitys.Ambito;
import logic.Entitys.Dimension;
import logic.Entitys.Perspectiva;
import logic.Entitys.Pregunta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Controlador {

    public void delete(Model model){
        updatePoints(model, 2);
        recursiveDelete(model.getId(), model.getLevel());
    }

    private void recursiveDelete(int id, int level){
        switch (level){
            case 1:
                DAOAmbito daoAmbito = new DAOAmbito();
                daoAmbito.deleteAmbito(id);
                DAOPerspectiva dao_Perspectiva = new DAOPerspectiva();
                Iterator<Perspectiva> iteratorP = dao_Perspectiva.consultPerspectivas(id).iterator();

                while(iteratorP.hasNext()){
                    recursiveDelete(iteratorP.next().getId_perspectiva(), level + 1);
                }
                break;

            case 2:
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                daoPerspectiva.deletePerspectiva(id);
                DAODimension dao_Dimension = new DAODimension();
                Iterator<Dimension> iteratorD = dao_Dimension.consultDimensiones(id).iterator();

                while(iteratorD.hasNext()){
                    recursiveDelete(iteratorD.next().getId_dimension(), level + 1);
                }
                break;

            case 3:
                DAODimension daoDimension = new DAODimension();
                daoDimension.deleteDimension(id);
                DAOPregunta dao_Pregunta = new DAOPregunta();
                dao_Pregunta.deletePreguntaForDimension(id);
                break;

            default:
                DAOPregunta daoPregunta = new DAOPregunta();
                daoPregunta.deletePregunta(id);
                break;
        }
    }

    public void updatePoints(Model model, int choise){
        int cant;
        switch (model.getLevel()){
            case 2:
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                recursiveUpdatePoints(model.getLevel() - 1, model.getSupId(), daoPerspectiva.findPerspectiva(model.getId()).getCant_dimensiones(), 2);
                break;

            case 3:
                DAODimension daoDimension = new DAODimension();
                recursiveUpdatePoints(model.getLevel() - 1, model.getSupId(), daoDimension.findDimension(model.getId()).getCant_preguntas(), 2);
                break;

            case 4:
                DAOPregunta daoPregunta = new DAOPregunta();
                recursiveUpdatePoints(model.getLevel() - 1, model.getSupId(), 1, choise);
                break;
            default:
                break;
        }
    }

    private void recursiveUpdatePoints(int level, int id, int cant, int choise){
        switch (level){
            case 1:
                DAOAmbito daoAmbito = new DAOAmbito();
                Ambito ambito = daoAmbito.findAmbito(id);

                if(choise == 1)
                    ambito.setCant_perspectivas(ambito.getCant_perspectivas() + cant);
                else
                    ambito.setCant_perspectivas(ambito.getCant_perspectivas() - cant);

                daoAmbito.updateAmbito(ambito, 2);
                break;
            case 2:
                DAOPerspectiva daoPerspectiva = new DAOPerspectiva();
                Perspectiva perspectiva = daoPerspectiva.findPerspectiva(id);

                if(choise == 1)
                    perspectiva.setCant_dimensiones(perspectiva.getCant_dimensiones() + cant);
                else
                    perspectiva.setCant_dimensiones(perspectiva.getCant_dimensiones() - cant);

                daoPerspectiva.updatePerspectiva(perspectiva, 2);
                recursiveUpdatePoints(level - 1, perspectiva.getId_ambito(), cant, choise);
                break;
            default:
                DAODimension daoDimension = new DAODimension();
                Dimension dimension = daoDimension.findDimension(id);

                if(choise == 1)
                    dimension.setCant_preguntas(dimension.getCant_preguntas() + cant);
                else
                    dimension.setCant_preguntas(dimension.getCant_preguntas() - cant);

                daoDimension.updateDimension(dimension, 2);
                recursiveUpdatePoints(level - 1, dimension.getId_perspectiva(), cant, choise);
                break;
        }
    }

    public void calculatePoints(){

    }

    private void recursiceCalculatePoints(){

    }

    private void addRecicleBin(){

    }

    public void insert(int level){
        switch (level){
            case 1:
                Ambito ambito = new Ambito();
                break;
            case 2:
                Perspectiva perspectiva = new Perspectiva();
                break;
            case 3:
                Dimension dimension = new Dimension();
                break;
            default:
                Pregunta pregunta = new Pregunta();
                break;
        }
    }

    public void recover(){

    }

    public ArrayList<Ambito> getAmbitos(){
        DAOAmbito dao = new DAOAmbito();
        return dao.consultAmbitos();
    }

    public ArrayList<Perspectiva> getPerspectiva(int id){
        DAOPerspectiva dao = new DAOPerspectiva();

        if(id > 0)
            return dao.consultPerspectivas(id);
        else
            return dao.consultPerspectivas();
    }

    public ArrayList<Dimension> getDimension(int id){
        DAODimension dao = new DAODimension();

        if(id > 0)
            return dao.consultDimensiones(id);
        else
            return dao.consultDimensiones();
    }

    public ArrayList<Pregunta> getPregunta(int id){
        DAOPregunta dao = new DAOPregunta();

        if(id > 0)
            return dao.consultPregunta(id);
        else
            return dao.consultPregunta();
    }
}
