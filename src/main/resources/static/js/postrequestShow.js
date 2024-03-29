$(document).ready(
    function() {

        // SUBMIT FORM
        $("#courseForm").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                courseId: $("#courseId").val(),
                courseName: $("#courseName").val(),
            }

            // DO POST
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "saveCourse", //is de url van je Mapping in de controller
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    if (result.status == "success") {
                        $("#postResultDiv").html("" + result.data.courseName
                            + "Post Successfully! <br>"
                            + "---> Congrats !!" + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }

            });

        }
    });