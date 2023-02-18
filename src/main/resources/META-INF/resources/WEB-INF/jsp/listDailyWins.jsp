<%@ include file="common/navigation.jspf" %>
<%@ include file="common/header.jspf" %>

<div class="container">
    <h1>Your Daily Wins</h1>
    <h5>Prepare 5 actions will bring you closer to your big goal. Time to accomplish them is 3-4 h.</h5>
    <h5>Good luck!</h5>
    <table class="table">
        <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Done?</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${dailyWins}" var="dailyWin">
                <tr>
                    <td>${dailyWin.description}</td>
                    <td>${dailyWin.targetDate}</td>
                    <td>${dailyWin.done}</td>
                    <td> <a href="delete-dailyWin?id=${dailyWin.id}" class="btn btn-warning">Delete</a> </td>
                    <td> <a href="update-dailyWin?id=${dailyWin.id}" class="btn btn-success">Update</a> </td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="add-dailyWin" class="btn btn-success">Add Daily Win</a>
</div>

<%@ include file="common/footer.jspf" %>
