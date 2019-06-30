//Variables
var monday = "MONDAY";
var tuesday = "TUESDAY";
var wednesday = "WEDNESDAY";
var thursday = "THURSDAY";
var friday = "FRIDAY";

//Constraint - Availability
function findAvailability(day, availabilityList) {
    for (i = 0; i < availabilityList.length; i++) {
        if (availabilityList[i].weekday === day) {
            return availabilityList[i];
        }
    }
}

function availAbilityMondayMo(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(mondayMoTeach);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op maandag ochtend");
        addHardConstraintClass(mondayMoTeach);
    } else {
        removeHardConstraintClass(mondayMoTeach);
    }
}

function availAbilityMondayAf(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(mondayAfTeach);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op maandag middag");
        addHardConstraintClass(mondayAfTeach);
    } else {
        removeHardConstraintClass(mondayAfTeach);
    }
}

function availabilityMondayEv(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(mondayEvTeach);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op maandag avond");
        addHardConstraintClass(mondayEvTeach);
    } else {
        removeHardConstraintClass(mondayEvTeach);
    }
}

function availabilityTuesdayMo(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(tuesdayMoTeach);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op dinsdag ochtend");
        addHardConstraintClass(tuesdayMoTeach);
    } else {
        removeHardConstraintClass(tuesdayMoTeach);
    }
}

function availabilityTuesdayAf(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(tuesdayAfTeach);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op dinsdag middag");
        addHardConstraintClass(tuesdayAfTeach);
    } else {
        removeHardConstraintClass(tuesdayAfTeach);
    }
}

function availabilityTuesdayEv(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(tuesdayEvTeach);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op dinsdag avond");
        addHardConstraintClass(tuesdayEvTeach);
    } else {
        removeHardConstraintClass(tuesdayEvTeach);
    }
}

function availabilityWednesdayMo(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(wednesdayMoTeach);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op woensdag ochtend");
        addHardConstraintClass(wednesdayMoTeach);
    } else {
        removeHardConstraintClass(wednesdayMoTeach);
    }
}

function availabilityWednesdayAf(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(wednesdayAfTeach);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op woensdag middag");
        addHardConstraintClass(wednesdayAfTeach);
    } else {
        removeHardConstraintClass(wednesdayAfTeach);
    }
}

function availabilityWednesdayEv(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(wednesdayEvTeach);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op woensdag avond");
        addHardConstraintClass(wednesdayEvTeach);
    } else {
        removeHardConstraintClass(wednesdayEvTeach);
    }
}

function availabilityThursdayMo(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(thursdayMoTeach);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op donderdag ochtend");
        addHardConstraintClass(thursdayMoTeach);
    } else {
        removeHardConstraintClass(thursdayMoTeach);
    }
}

function availabilityThursdayAf(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(thursdayAfTeach);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op donderdag middag");
        addHardConstraintClass(thursdayAfTeach);
    } else {
        removeHardConstraintClass(thursdayAfTeach);
    }
}

function availabilityThursdayEv(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(thursdayEvTeach);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op donderdag avond");
        addHardConstraintClass(thursdayEvTeach);
    } else {
        removeHardConstraintClass(thursdayEvTeach);
    }
}

function availabilityFridayMo(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(fridayMoTeach);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op vrijdag ochtend");
        addHardConstraintClass(fridayMoTeach);
    } else {
        removeHardConstraintClass(fridayMoTeach);
    }
}

function availabilityFridayAf(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(fridayAfTeach);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op vrijdag middag");
        addHardConstraintClass(fridayAfTeach);
    } else {
        removeHardConstraintClass(fridayAfTeach);
    }
}

function availabilityFridayEv(teacher, availability) {
    if (!availability) {
        noAvailabilityAlert(teacher);
        addHardConstraintClass(fridayEvTeach);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op vrijdag avond");
        addHardConstraintClass(fridayEvTeach);
    } else {
        removeHardConstraintClass(fridayEvTeach);
    }
}


function findTeacher(teacherUserName) {
    for (i = 0; i < teacherObject.length; i++) {
        if (teacherObject[i].username === teacherUserName) {
            return teacherObject[i];
        }
    }
}

function noAvailabilityAlert(teacher) {
    alert("Docent " + teacher.username + " heeft zijn/haar beschikbaarheid helaas nog niet opgegeven! " +
        "Graag de bettrefende docent hierop attenderen!")
}