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
    var availability = findAvailability(monday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op maandag ochtend");
        if (!mondayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayMoTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (mondayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayMoTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

mondayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayAfTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op maandag middag");
        if (!mondayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayAfTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (mondayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayAfTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

mondayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayEvTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op maandag avond");
        if (!mondayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayEvTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (mondayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            mondayEvTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

tuesdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayMoTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op dinsdag ochtend");
        if (!tuesdayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            tuesdayMoTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (tuesdayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            tuesdayMoTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

tuesdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayAfTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op dinsdag middag");
        if (!tuesdayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            tuesdayAfTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (tuesdayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            tuesdayAfTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

tuesdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayEvTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op dinsdag avond");
        if (!tuesdayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            tuesdayEvTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (tuesdayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            tuesdayEvTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

wednesdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayMoTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op woensdag ochtend");
        if (!wednesdayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            wednesdayMoTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (wednesdayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            wednesdayMoTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

wednesdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayAfTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op woensdag middag");
        if (!wednesdayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            wednesdayAfTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (wednesdayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            wednesdayAfTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

wednesdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayEvTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op woensdag avond");
        if (!wednesdayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            wednesdayEvTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (wednesdayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            wednesdayEvTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

thursdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayMoTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op donderdag ochtend");
        if (!thursdayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            thursdayMoTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (thursdayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            thursdayMoTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

thursdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayAfTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op donderdag middag");
        if (!thursdayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            thursdayAfTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (thursdayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            thursdayAfTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

thursdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayEvTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op donderdag avond");
        if (!thursdayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            thursdayEvTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (thursdayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            thursdayEvTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

fridayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayMoTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.morning) {
        alert("Docent " + teacher.username + " is niet beschikbaar op vrijdag ochtend");
        if (!fridayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            fridayMoTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (fridayMoTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            fridayMoTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

fridayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayAfTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.afternoon) {
        alert("Docent " + teacher.username + " is niet beschikbaar op vrijdag middag");
        if (!fridayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            fridayAfTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (fridayAfTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            fridayAfTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

fridayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayEvTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);

    if (!availability) {
        noAvailabilityAlert(teacher);

    } else if (!availability.evening) {
        alert("Docent " + teacher.username + " is niet beschikbaar op vrijdag avond");
        if (!fridayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            fridayEvTeach.parentNode.classList.add(availabilityConstraintClass);
        }
    } else {
        if (fridayEvTeach.parentNode.classList.contains(availabilityConstraintClass)) {
            fridayEvTeach.parentNode.classList.remove(availabilityConstraintClass);
        }
    }
});

function findTeacher(teacherUserName) {
    for (i = 0; i < teacherObject.length; i++) {
        if (teacherObject[i].username === teacherUserName) {
            return teacherObject[i];
        }
    }
}

function findAvailability(day, availabilityList) {
    for (i = 0; i < availabilityList.length; i++) {
        if (availabilityList[i].weekday === day) {
            return availabilityList[i];
        }
    }
}

function noAvailabilityAlert(teacher) {
    alert("Docent " + teacher.username + " heeft zijn/haar beschikbaarheid helaas nog niet opgegeven! " +
        "Graag de bettrefende docent hierop attenderen!")
}