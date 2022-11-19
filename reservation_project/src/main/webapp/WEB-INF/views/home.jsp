<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>
<style>
    .ranking-list:hover {
        animation: box-ani 0.5s linear forwards;
    }

    @keyframes box-ani {
        from {
            transform: translate(0, 0);

        }
        to {
            transform: translate(0, -15px);
        }

    }

    .text-center > h2 {
        color: rgba(0, 82, 255, 0.9);
    }

    .text-center > h2::after {
        color: rgba(0, 82, 255, 0.9);
        content: "";
    }

    .col-xs-12 > h2::after {
        content: "";
    }

    .custom-sm-btn {
        padding: 0px;
        width: 135px;
        height: 45px;
    }

    .custom-sm-btn:hover {
        text-decoration: none;
        color: white;
    }

    .fa {
        color: rgba(0, 82, 255, 0.9);
    }

    p {
        font-family: 'SUIT-Medium';
    }

    .img-responsive {
        width: 600px;
        height: 600px;
    }

    .custom-img {
        border-radius: 10px;
    }
</style>
<div class="section-container">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-8 col-md-offset-2">
                <div class="text-center">
                    <h2>BANG</h2>
                    <p>
                        BANG은 놀라움를 의미합니다. <br> !가 문장 안에서의 놀라움을 나타내듯이, <br> 우리의 일상에도 !를 찍는 행복이 필요합니다. <br> BANG과 함께 일상에
                        !를 찍어보세요.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="section-container">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">

                <div id="carousel-example-generic" class="carousel carousel-fade slide" data-ride="carousel">

                    <div class="carousel-inner" role="listbox">

                        <div class="item active">
                            <img class="img-responsive"
                                 src="https://image.shutterstock.com/image-photo/casual-vintage-boots-on-gray-600w-531640540.jpg"
                                 style="height: 700px; width: 800px" alt="First slide">
                            <div class="carousel-caption card-shadow reveal">

                                <h3>FUN</h3>
                                <a class="left carousel-control" href="#carousel-example-generic" role="button"
                                   data-slide="prev"> <i class="fa fa-chevron-left" aria-hidden="true"></i> <span
                                        class="sr-only">Previous</span>
                                </a> <a class="right carousel-control" href="#carousel-example-generic" role="button"
                                        data-slide="next"> <i class="fa fa-chevron-right" aria-hidden="true"></i> <span
                                    class="sr-only">Next</span>
                            </a>
                                <p>Family, Friend</p>

                                <p>좋은 곳에서 놀라운 하루를..</p>
                                <br> <a href="user/house-list" class="custom-sm-btn" title=""> BANG 예약 </a>

                            </div>
                        </div>
                        <div class="item">
                            <img class="img-responsive"
                                 src="https://image.shutterstock.com/image-illustration/hotel-sign-stars-3d-illustration-600w-195879770.jpg"
                                 style="height: 700px; width: 800px; object-fit: cover;">
                            <div class="carousel-caption card-shadow reveal">

                                <h3>Be Host</h3>
                                <a class="left carousel-control" href="#carousel-example-generic" role="button"
                                   data-slide="prev"> <i class="fa fa-chevron-left" aria-hidden="true"></i> <span
                                        class="sr-only">Previous</span>
                                </a> <a class="right carousel-control" href="#carousel-example-generic" role="button"
                                        data-slide="next"> <i class="fa fa-chevron-right" aria-hidden="true"></i> <span
                                    class="sr-only">Next</span>
                            </a>
                                <p>누구나 호스트가 될 수 있습니다.</p>
                                <p>우리는 모두에게 같은 기회가 있습니다.</p>
                                <br> <a href="guest/be-host" class="custom-sm-btn" title=""> BANGER로 전환 </a>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

    </div>
</div>

<div class="section-container">
    <div class="container text-center">

        <div class="row section-container-spacer">

            <div class="col-xs-12 col-md-12">
                <h2 style="color: rgba(0,82,255,0.9);">Best BANG</h2>
                <p>BANG 랭킹</p>
            </div>
        </div>

        <div class="row">
            <c:forEach var="house" items="${houses}">
                <%!int number = 1;%>
                <div class="ranking-list col-xs-12 col-md-4">
                    <a href="/user/house-detail/${house.id}"> <img class="custom-img"
                                                                   src="http://localhost:9090/upload/${house.image.imageUrl}"
                                                                   class="
						reveal img-responsive
						reveal-contentimage-center" style="width: 210px; height: 210px; object-fit: cover;"></a>
                    <h3 style="color: rgb(0,255,11);">
                        Best
                        <%=number++%>
                    </h3>

                </div>
            </c:forEach>
            <%
                number = 1;
            %>
        </div>
    </div>
</div>
<div class="section-container">
    <div class="container text-center">
        <div class="row section-container-spacer">
            <div class="col-xs-12 col-md-12">
                <h2 class="text-center" style="color: rgba(0,82,255,0.9);">Our Service</h2>
                <p>
                    BANG에서 제공하는 서비스 <br>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8 col-md-3">
                <i class="bi bi-calendar-check" style="font-size: 170px; color: rgb(21,21,21);"></i>
                <h3 style="color: rgb(205,255,0);">Delicious Day</h3>
            </div>
            <div class="col-xs-8  col-md-3">
                <i class="bi bi-emoji-smile" style="font-size: 170px; color: rgb(21,21,21);"></i>
                <h3 style="color: rgb(205,255,0);">Happiness</h3>
            </div>
            <div class="col-xs-8  col-md-3">
                <i class="bi bi-Journal-plus" style="font-size: 170px; color: rgb(21,21,21);"></i>
                <h3 style="color: rgb(205,255,0); font-style: bold;">WishList</h3>
            </div>
            <div class="col-xs-8 col-md-3">
                <i class="bi bi-pencil-square" style="font-size: 170px; color: rgb(21,21,21);"></i>
                <h3 style="color: rgb(205,255,0); font-style: bold;">Review</h3>
            </div>
        </div>
        <br><br>
    </div>
</div>
<%@ include file="layout/footer.jsp" %>