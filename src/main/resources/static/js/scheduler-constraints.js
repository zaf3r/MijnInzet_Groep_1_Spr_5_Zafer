
mondayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayMoTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);
    availAbilityMondayMo(teacher, availability);

    if (!mondayMoTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateMonday,morning, mondayMoTeach);
    }

    if (!mondayMoTeach.parentNode.classList.contains(HardConstraint)) {
        incidentClash();
    }
});

mondayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayAfTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);
    availAbilityMondayAf(teacher, availability);

    if (!mondayAfTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateMonday,afternoon, mondayAfTeach);
    }

});


mondayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayEvTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);
    availabilityMondayEv(teacher, availability);

    if (!mondayEvTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateMonday,evening, mondayEvTeach);
    }
});

tuesdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayMoTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);
    availabilityTuesdayMo(teacher, availability);

    if (!tuesdayMoTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateTuesday,morning, tuesdayMoTeach);
    }
});

tuesdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayAfTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);
    availabilityTuesdayAf(teacher, availability)

    if (!tuesdayAfTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateTuesday,afternoon, tuesdayAfTeach);
    }
});

tuesdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayEvTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);
    availabilityTuesdayEv(teacher, availability);

    if (!tuesdayEvTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateTuesday,evening, tuesdayEvTeach);
    }
});

wednesdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayMoTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);
    availabilityWednesdayMo(teacher, availability);

    if (!wednesdayMoTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateWednesday,morning, wednesdayMoTeach);
    }
});

wednesdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayAfTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);
    availabilityWednesdayAf(teacher, availability);

    if (!wednesdayAfTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateWednesday,afternoon, wednesdayAfTeach);
    }
});

wednesdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayEvTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);
    availabilityWednesdayEv(teacher, availability);

    if (!wednesdayEvTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateWednesday,evening, wednesdayEvTeach);
    }
});

thursdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayMoTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);
    availabilityThursdayMo(teacher, availability);

    if (!thursdayMoTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateThursday,morning, thursdayMoTeach);
    }
});

thursdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayAfTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);
    availabilityThursdayAf(teacher, availability);

    if (!thursdayAfTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateThursday,afternoon, thursdayAfTeach);
    }
});

thursdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayEvTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);
    availabilityThursdayEv(teacher, availability);

    if (!thursdayEvTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateThursday,evening, thursdayEvTeach);
    }
});

fridayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayMoTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);
    availabilityFridayMo(teacher, availability);

    if (!fridayMoTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateFriday,morning, fridayMoTeach);
    }
});

fridayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayAfTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);
    availabilityFridayAf(teacher, availability);

    if (!fridayAfTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateFriday,afternoon, fridayAfTeach);
    }
});

fridayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayEvTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);
    availabilityFridayEv(teacher, availability);

    if (!fridayEvTeach.parentNode.classList.contains(HardConstraint)) {
        cohortClash(teacher,dateFriday,evening, fridayEvTeach);
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