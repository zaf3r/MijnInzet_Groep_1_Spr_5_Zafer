//Variables
var monday = "MONDAY";
var tuesday = "TUESDAY";
var wednesday = "WEDNESDAY";
var thursday = "THURSDAY";
var friday = "FRIDAY";
var availabilityConstraintClass = "availabilityConstraint";

//Constraint - Availability
mondayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayMoTeach.value);
    var availability =  findAvailability(monday, teacher.availabilityList);

    if(!availability) {
        alert("Docent " + teacher.username + " heeft zijn/haar beschikbaarheid helaas nog niet opgegeven! " +
            "Graag de bettrefende docent hierop attenderen!")

        } else if(!availability.morning) {
        if(!mondayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayMoTeach.parentNode.classList.add(availabilityConstraintClass);
            alert("Docent " + teacher.username + " is niet beschikbaar op maandag ochtend")
        } else {
            if(mondayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
                mondayMoTeach.parentNode.classList.remove(availabilityConstraintClass);
            }
        }
    }
});

function findTeacher(teacherUserName) {
    for(i = 0; i<teacherObject.length; i++) {
        if(teacherObject[i].username === teacherUserName) {
            return teacherObject[i];
        }
    }
}

function findAvailability(day, availabilityList ) {
    for(i = 0; i<availabilityList.length; i++) {
        if(availabilityList[i].weekday === day) {
            return availabilityList[i];
        }
    }
}