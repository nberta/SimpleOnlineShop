$(document).ready(function () {
    $(".approveSeller").click(function () {
        let userId = $(this).data("userId");
        $.ajax("/admin/approve-seller/" + sellerId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (textStatus) {
            alert("Request failed: " + textStatus);
        });
    });

    $(".rejectSeller").click(function () {
        setUpCsrfToken();
        let sellerId = $(this).data("sellerId");

        $.ajax("/admin/reject-seller/" + sellerId,
            {type: "PUT"})
            .done(function (result) {
                location.reload();
            }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});