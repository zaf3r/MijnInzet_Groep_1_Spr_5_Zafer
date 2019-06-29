mondayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayMoTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);
    availAbilityMondayMo(teacher, availability);
});



mondayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayAfTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);
    availAbilityMondayAf(teacher, availability);
});



mondayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(mondayEvTeach.value);
    var availability = findAvailability(monday, teacher.availabilityList);
    availabilityMondayEv(teacher, availability)
});

tuesdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayMoTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);
    availabilityTuesdayMo(teacher, availability);
});

tuesdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayAfTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);
    availabilityTuesdayAf(teacher, availability)
});

tuesdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(tuesdayEvTeach.value);
    var availability = findAvailability(tuesday, teacher.availabilityList);
    availabilityTuesdayEv(teacher, availability);
});

wednesdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayMoTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);
    availabilityWednesdayMo(teacher, availability);
});

wednesdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayAfTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);
    availabilityWednesdayAf(teacher, availability);
});

wednesdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(wednesdayEvTeach.value);
    var availability = findAvailability(wednesday, teacher.availabilityList);
    availabilityWednesdayEv(teacher, availability);
});

thursdayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayMoTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);
    availabilityThursdayMo(teacher, availability);
});

thursdayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayAfTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);
    availabilityThursdayAf(teacher, availability);
});

thursdayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(thursdayEvTeach.value);
    var availability = findAvailability(thursday, teacher.availabilityList);
    availabilityThursdayEv(teacher, availability);
});

fridayMoTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayMoTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);
    availabilityFridayMo(teacher, availability);
});

fridayAfTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayAfTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);
    availabilityFridayAf(teacher, availability);
});

fridayEvTeach.addEventListener("change", function () {
    var teacher = findTeacher(fridayEvTeach.value);
    var availability = findAvailability(friday, teacher.availabilityList);
    availabilityFridayEv(teacher, availability);
});