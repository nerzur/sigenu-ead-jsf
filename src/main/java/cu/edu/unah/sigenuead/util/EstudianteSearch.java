package cu.edu.unah.sigenuead.util;

import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EstudianteSearch implements Comparable {

    Estudiante estudiante;
    int searchTimes;
    FacultadCumCarreraEstudiante facultadCumCarreraEstudiante;

    @Override
    public int compareTo(Object o) {
        return this.searchTimes - ((EstudianteSearch) o).searchTimes;
    }
}
