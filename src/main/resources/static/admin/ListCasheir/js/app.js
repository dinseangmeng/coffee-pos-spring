 function handleStatusChange(event){
    let id=(event.target.getAttribute('data-id'));


    if(id){
     fetch(`/v1/cp/user/switch-status/${id}`,{
        method: 'POST'
     }).then(res=>res.json())
     .then(data=>{
        Swal.fire({
            toast: true,
            position: 'top-end',
            timerProgressBar: true,
            icon: 'info',
            title: `${data.message}`,
            showConfirmButton: false,
            timer: 2000
        })
     })
    }
}