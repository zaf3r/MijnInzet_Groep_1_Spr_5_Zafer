// GET: $(document).ready(
//     function() {
//
//         // GET REQUEST
//         $("#getALlCourses").click(function(event) {
//             event.preventDefault();
//             ajaxGet();
//         });
//
//         // DO GET
//         function ajaxGet() {
//             $.ajax({
//                 type : "GET",
//                 url : "getCourses",//als de controller
//                 success : function(result) {
//                     if (result.status == "success") {
//                         $('#getResultDiv ul').empty();
//                         var custList = "";
//                         $.each(result.data,
//                             function(i, course) {
//                                 var user = "Book Coursee  "
//                                     + course.CourseName
//                                     + "<br>";
//                                 $('#getResultDiv .list-group').append(
//                                     user)//cohort
//                             });
//                         console.log("Success: ", result);
//                     } else {
//                         $("#getResultDiv").html("<strong>Error</strong>");
//                         console.log("Fail: ", result);
//                     }
//                 },
//                 error : function(e) {
//                     $("#getResultDiv").html("<strong>Error</strong>");
//                     console.log("ERROR: ", e);
//                 }
//             });
//         }
//     })