/**
 * 对问题评论
 */
function commentQuestion() {
    let questionId = $("#questionId").val();
    let content = $("#content").val();
    commentTarget(questionId, 1, content);
}

/**
 * 对评论评论
 */
function commentComment(e) {
    let commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    commentTarget(commentId, 2, content);
}


/**
 * 评论
 * @param targetId
 * @param type
 * @param content
 */
function commentTarget(targetId, type, content) {
    if (!content) {
        alert("请输入评论内容")
        return;
    }
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: '/comment',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (res) {
            if (res.code == 0) {
                window.location.reload();
            } else {
                if (res.code == 1001) {
                    var isAccepted = confirm(res.msg);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=d58131a8786b874d602c&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(res.msg);
                }
            }
        },
        dataType: "json"
    });
}

function collapse(e) {
  var commentId=  e.getAttribute("data-id")
    var commentItem=$('#comment-'+commentId);
    var collapse=  e.getAttribute("data-collapse");
    if (collapse) {
        commentItem.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("action")
    }else{
        $.getJSON( "/comment/"+commentId, function( res ) {
            console.log(res.data);
            $("#commentRoot-"+commentId).empty().append(res.data)
            console.log(res.data)
            commentItem.addClass("in");
            e.setAttribute("data-collapse","in");
            e.classList.add("action")
        });
    }
}

function selectTag(e) {
    console.log("select")
}