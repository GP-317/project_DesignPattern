package appli;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * Permet de recuperer les informations 
 * necessaires sans que l'utilisateur est accés a la classe
 * dite TEAMSAttendanceList
 * 
 * Iterator est une interface qui utilise framework collection.
 * Il nous laisse patrouiller la collection , 
 * afin d'aborder l'élément.
 * 
 * @author Ahmet
 *
 */
public class MyIterator {
	
    private LinkedList<String> _attlist = null;
    private ArrayList<LocalDateTime> listeDate;
    private LocalDateTime heureMin = null;
    private LocalDateTime heureMax = null;
    private String date = null;

    /**
     * Constructeur de la classe myIterator
     * 
     * @param _file attribut de type File qui recupere un ficier via TEAMSAttendanceList 
     */
		public MyIterator(File _file) {
				var teamsFile = new TEAMSAttendanceList(_file);
				var lines = teamsFile.get_attlist();
					if (lines != null) {
						_attlist = teamsFile.get_attlist();
						processList();
					   }
		}
	/**
	 * Ressort les informations rechercher sous forme de liste (date et heures)
	 */
		private void processList() {
				if (this._attlist != null) {
						this.listeDate = new ArrayList<LocalDateTime>();
            // On utilise iterator
						Iterator<String> element = this._attlist.iterator();
						element.next();
						while (element.hasNext()) {
							String input = element.next();

							String[] infos = input.split("\t");
         
								if (infos.length==1) {
									String[] liste = infos[0].split(",");
              
									String instant = liste[2];
									this.listeDate.add(TEAMSDateTimeConverter.StringToLocalDateTime(instant));
									}
								}
			// On utilise iterator
						Iterator<LocalDateTime> parcoursListeDate = this.listeDate.iterator();
						while(parcoursListeDate.hasNext()) {
							LocalDateTime current = parcoursListeDate.next();
								if(heureMin == null) {
									heureMin = current;
								   }else {
									   if(heureMin.isAfter(current)) {
										   heureMin = current;
									   	 }
								   }
								
								if(heureMax == null) {
									heureMax = current;
								  } else {
									  if(heureMax.isBefore(current)) {
										  heureMax = current;
									    }
								  }
						}
           
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						date = heureMin.format(formatter);
				   }
		}
	/**
	 * donne l'heure minimum
	 * @return objet de type LocalDateTime
	 */
		public LocalDateTime getHeureMin() {
			return this.heureMin;
		}
	/**
	 * donne l'heure maximum
	 * @return objet de type LocalDateTime
	 */
		public LocalDateTime getHeureMax() {
			return this.heureMax;
		}
	/**
	 * donne la date
	 * @return objet de type String
	 */
		public String getDate() {
			return this.date;
		}
}