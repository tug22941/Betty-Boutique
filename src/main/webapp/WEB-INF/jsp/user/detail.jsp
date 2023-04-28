<jsp:include page="../include/header.jsp" />

<style>
    a{
        color:black;
        text-decoration:none;
    }
</style>

<section py-5 bg-dark-grey>
    <div class="container text-center">
        <h1>User Detail</h1>
    </div>
</section>

<script>
    function toEdits(){
        location.href="/user/edit/${user.id}";
    }
</script>

<section class="py-5 bg-purple">
    <div class="container text-center d-flex">
        <table class="table table-striped">
            <tbody>
            <tr>
                <td>Edit User</td>
                <td>
                    <button type="button" class="btn btn-sm btn-danger" onclick="toEdits()">Edit User Details</button>
                </td>
            </tr>
            <tr>
                <td>ID #</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>First Name</td>
                <td>${user.firstName}</td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>${user.lastName}</td>
            </tr>
            <tr>
                <td>Email Address</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>Password</td>
                <td>${user.password}</td>
            </tr>
            </tbody>
        </table>
    </div>
</section>


<jsp:include page="../include/footer.jsp" />