/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.util;

import cu.edu.unah.sigenuead.entity.Authorities;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.CumServices;
import cu.edu.unah.sigenuead.service.CursoServices;
import cu.edu.unah.sigenuead.service.ExamenServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.MatriculaServices;
import cu.edu.unah.sigenuead.service.MunicipioServices;
import cu.edu.unah.sigenuead.service.PaisServices;
import cu.edu.unah.sigenuead.service.PlanestudioServices;
import cu.edu.unah.sigenuead.service.ProvinciaServices;
import cu.edu.unah.sigenuead.service.UniversidadServices;
import cu.edu.unah.sigenuead.service.UserServices;

/**
 *
 * @author claudy
 */
public class test {
    
    public static void main(String[] args) {
//        PaisServices ps=new PaisServices();
//        ps.addPais("Cuba", false);
//        ProvinciaServices pvs=new ProvinciaServices();
//        pvs.addProvincia("Mayabeque", "Cuba", false);
//        MunicipioServices ms=new MunicipioServices();
//        ms.addMunicipio("San Jose", "Mayabeque", "Cuba", false);
//        UniversidadServices us=new UniversidadServices();
//        us.editUniversidad("1234", "Universidad jfchgvh,g Agraria La Habana", "askcmaks", "skjdjnjn", "anscjan", "jdnosnd", "zjdsn", "sdnvvosbdv", "sndvowsnvdo", "lsndvown", false, "San Jose", "Mayabeque");
//        CursoServices cs=new CursoServices();
//        System.out.println(cs.generateCode());
//        cs.addCurso(cs.generateCode(), new Date(119, 8, 1),new Date(119, 8, 5), new Date(119, 9, 7), new Date(119, 9, 10), new Date(120, 9, 4), true);
//        FacultadServices fs=new FacultadServices();
//        fs.deleteFacultad("123");
//        CumServices cs=new CumServices();
//        cs.deleteCum("121312");
//        UserServices us=new UserServices();
//        ArrayList<String> a=new ArrayList<String>();
//        a.add("ROLE_ADMIN");
//        us.addUsers("claudiaar", "96092005238", "Claudia", "Aguilar", "Rajme", "claudiaar@unah.edu.cu", "start", true, "usuario de prueba",a);
//        CarreraServices cs=new CarreraServices();
//        cs.getInstanceOfCarrera().findCarreraAvailableByFacultad("Ciencias Tecnicas");
//        System.out.println("sjdbcjhdc");
        PlanestudioServices ps=new PlanestudioServices();
        List<String> g=ps.findPlanestudioAvailableByCarrera("cvbnm", "dfghjkl");
        System.out.println(g.size());
    }
}
