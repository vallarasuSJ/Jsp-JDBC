<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TypeScript Todo</title>

        <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous"
      />
      <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"
      ></script>
      </head>
    <body>
    <form action="Logout.jsp" method="link" class="ms-5">
    <input type="submit" value="Logout" style="background-color: red;color: #fff;  border: none;  border-radius: 5px; padding: 5px 10px; cursor: pointer; margin-top:5px"/>
    </form>
      <div class="vh-100 bg-dark-subtle text-emphasis-dark">
        <div class="container ">
            <div class="header" style="padding-bottom: 40px">
              <h1 style="text-align: center">Todo</h1>
            </div>


            <!-- Todo -->
            <div class="row g-3" style="text-align: center; justify-content: center">
              <div class="col-auto">
               <form action="home" method="post">
                <label for="Enter your todo:" class="visually-hidden"></label>
                <input
                  type="text"
                  class="form-control"
                  id="input"
                  name="todo"
                  value="${todo.todo}"
                  placeholder="Enter your todo"
                />
              </div>
              <div class="col-auto">
                <input type="submit" id="addTodo" value="Add" class="btn btn-primary mb-3"></input>
              </div>
              </form>
        </div>


       <%
            if(session.getAttribute("id") == null){
                 response.sendRedirect(request.getContextPath());
            }
        %>



          <c:if test="${todos.size() eq 0}">
                <h3 class="text-center font-weight-bold m-5 p-5">No Items to display :(</h3>
            </c:if>

            <c:if test="${todos.size() gt 0}">
                <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Todo</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="todo" items="${todos}">
                                <tr>
                                    <td>
                                        <c:out value="${todo.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${todo.todo}" />
                                    </td>
                                    <td><a href="home?id=<c:out value='${todo.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
            </c:if>


    </body>
    </html>