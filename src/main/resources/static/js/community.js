/**
 * 提交回复
 */
function post() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    comment2target(questionId, 1, content)

}

function comment(e) {

    let commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}


function comment2target(targetId, type, content) {
    if (!content) {
        alert("请输入回复内容");
        return
    }
    console.log(targetId);
    console.log(content);
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {

            if (response.code == 200) {
                window.location.reload()
            } else {
                if (response.code == 2003) {
                    var isAccpted = confirm(response.message);

                    if (isAccpted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=08937290359057a5f107&redirect_uri=http://localhost:8886/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message)
                }

            }

        },

        dataType: "json"
    });

}


/**
 * 展开二级评论
 */
var count = 0;

function collapseComments(e) {

    let id = e.getAttribute("id");
    console.log(id);
    let cid = '#' + id;
    var subCommentContainer = $("#comment-" + id);
    if (!$(cid).hasClass("community-comment-btn")) {
        if (subCommentContainer.children().length == 1) {
            $.getJSON("/comment/" + id, function (data) {

                $.each(data.data.reverse(), function (index, comment) {


                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));
                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media community-media"
                    }).append(mediaLeftElement).append(mediaBodyElement);


                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 community-left-review",

                    }).append(mediaElement);
                    ;

                    subCommentContainer.prepend(commentElement);
                });

            });


            $(cid).addClass("community-comment-btn")

        }
        else {
            $(cid).addClass("community-comment-btn")
        }
    } else {


        $(cid).removeClass("community-comment-btn")

    }

}