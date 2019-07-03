mondayMoTeach.addEventListener("change", function () {

    var teacher = findTeacher(mondayMoTeach.value);

    var availability = findAvailability(monday, teacher.availabilityList);

    removeHardConstraintClass(mondayMoTeach);

    availAbilityMondayMo(teacher, availability);


    if (!mondayMoTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateMonday, morning, mondayMoTeach);

    }


    if (!mondayMoTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    } else {


    }

});


mondayAfTeach.addEventListener("change", function () {

    var teacher = findTeacher(mondayAfTeach.value);

    var availability = findAvailability(monday, teacher.availabilityList);

    removeHardConstraintClass(mondayAfTeach);

    availAbilityMondayAf(teacher, availability);


    if (!mondayAfTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateMonday, afternoon, mondayAfTeach);

    }


    if (!mondayAfTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


mondayEvTeach.addEventListener("change", function () {

    var teacher = findTeacher(mondayEvTeach.value);

    var availability = findAvailability(monday, teacher.availabilityList);

    removeHardConstraintClass(mondayEvTeach);

    availabilityMondayEv(teacher, availability);


    if (!mondayEvTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateMonday, evening, mondayEvTeach);

    }


    if (!mondayEvTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


tuesdayMoTeach.addEventListener("change", function () {

    var teacher = findTeacher(tuesdayMoTeach.value);

    var availability = findAvailability(tuesday, teacher.availabilityList);

    removeHardConstraintClass(tuesdayMoTeach);

    availabilityTuesdayMo(teacher, availability);


    if (!tuesdayMoTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateTuesday, morning, tuesdayMoTeach);

    }


    if (!tuesdayMoTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


tuesdayAfTeach.addEventListener("change", function () {

    var teacher = findTeacher(tuesdayAfTeach.value);

    var availability = findAvailability(tuesday, teacher.availabilityList);

    removeHardConstraintClass(tuesdayAfTeach);

    availabilityTuesdayAf(teacher, availability);


    if (!tuesdayAfTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateTuesday, afternoon, tuesdayAfTeach);

    }


    if (!tuesdayAfTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


tuesdayEvTeach.addEventListener("change", function () {

    var teacher = findTeacher(tuesdayEvTeach.value);

    var availability = findAvailability(tuesday, teacher.availabilityList);

    removeHardConstraintClass(tuesdayEvTeach);

    availabilityTuesdayEv(teacher, availability);


    if (!tuesdayEvTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateTuesday, evening, tuesdayEvTeach);

    }


    if (!tuesdayEvTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


wednesdayMoTeach.addEventListener("change", function () {

    var teacher = findTeacher(wednesdayMoTeach.value);

    var availability = findAvailability(wednesday, teacher.availabilityList);

    removeHardConstraintClass(wednesdayMoTeach);

    availabilityWednesdayMo(teacher, availability);


    if (!wednesdayMoTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateWednesday, morning, wednesdayMoTeach);

    }


    if (!wednesdayMoTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


wednesdayAfTeach.addEventListener("change", function () {

    var teacher = findTeacher(wednesdayAfTeach.value);

    var availability = findAvailability(wednesday, teacher.availabilityList);

    removeHardConstraintClass(wednesdayAfTeach);

    availabilityWednesdayAf(teacher, availability);


    if (!wednesdayAfTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateWednesday, afternoon, wednesdayAfTeach);

    }


    if (!wednesdayAfTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


wednesdayEvTeach.addEventListener("change", function () {

    var teacher = findTeacher(wednesdayEvTeach.value);

    var availability = findAvailability(wednesday, teacher.availabilityList);

    removeHardConstraintClass(wednesdayEvTeach);

    availabilityWednesdayEv(teacher, availability);


    if (!wednesdayEvTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateWednesday, evening, wednesdayEvTeach);

    }


    if (!wednesdayEvTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


thursdayMoTeach.addEventListener("change", function () {

    var teacher = findTeacher(thursdayMoTeach.value);

    var availability = findAvailability(thursday, teacher.availabilityList);

    removeHardConstraintClass(thursdayMoTeach);

    availabilityThursdayMo(teacher, availability);


    if (!thursdayMoTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateThursday, morning, thursdayMoTeach);

    }


    if (!thursdayMoTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


thursdayAfTeach.addEventListener("change", function () {

    var teacher = findTeacher(thursdayAfTeach.value);

    var availability = findAvailability(thursday, teacher.availabilityList);

    removeHardConstraintClass(thursdayAfTeach);

    availabilityThursdayAf(teacher, availability);


    if (!thursdayAfTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateThursday, afternoon, thursdayAfTeach);

    }


    if (!thursdayAfTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


thursdayEvTeach.addEventListener("change", function () {

    var teacher = findTeacher(thursdayEvTeach.value);

    var availability = findAvailability(thursday, teacher.availabilityList);

    removeHardConstraintClass(thursdayEvTeach);

    availabilityThursdayEv(teacher, availability);


    if (!thursdayEvTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateThursday, evening, thursdayEvTeach);

    }


    if (!thursdayEvTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


fridayMoTeach.addEventListener("change", function () {

    var teacher = findTeacher(fridayMoTeach.value);

    var availability = findAvailability(friday, teacher.availabilityList);

    removeHardConstraintClass(fridayMoTeach);

    availabilityFridayMo(teacher, availability);


    if (!fridayMoTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateFriday, morning, fridayMoTeach);

    }


    if (!fridayMoTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


fridayAfTeach.addEventListener("change", function () {

    var teacher = findTeacher(fridayAfTeach.value);

    var availability = findAvailability(friday, teacher.availabilityList);

    removeHardConstraintClass(fridayAfTeach);

    availabilityFridayAf(teacher, availability);


    if (!fridayAfTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateFriday, afternoon, fridayAfTeach);

    }


    if (!fridayAfTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }


});


fridayEvTeach.addEventListener("change", function () {

    var teacher = findTeacher(fridayEvTeach.value);

    var availability = findAvailability(friday, teacher.availabilityList);

    removeHardConstraintClass(fridayEvTeach);

    availabilityFridayEv(teacher, availability);


    if (!fridayEvTeach.parentNode.classList.contains(HardConstraint)) {

        cohortClash(teacher, dateFriday, evening, fridayEvTeach);

    }


    if (!fridayEvTeach.parentNode.classList.contains(HardConstraint)) {

        incidentClash();

    }

});


function removeHardConstraintClass(dayPartTeacher) {

    if (dayPartTeacher.parentNode.classList.contains(HardConstraint)) {

        dayPartTeacher.parentNode.classList.remove(HardConstraint);

    }

}


function addHardConstraintClass(dayPartTeacher) {

    if (!dayPartTeacher.parentNode.classList.contains(HardConstraint)) {

        dayPartTeacher.parentNode.classList.add(HardConstraint);

    }

}