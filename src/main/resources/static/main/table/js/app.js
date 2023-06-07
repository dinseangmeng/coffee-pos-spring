const btns=document.querySelector(".left")
let data;
let isSelected=false;
let tableId="";
btns.addEventListener('click',(e)=>{
    const target=e.target;
    if(target.classList.contains('btn')){
        tableId=target.getAttribute("data-id")
        document.querySelectorAll(".btn").forEach(item=>{
            item.classList.remove("active")
        })
        target.classList.add("active")

        document.querySelector(".content").innerHTML=`<div class="lds-facebook"><div></div><div></div><div></div></div>`
        const id=target.getAttribute("data-id")
        fetch(`/v1/tables/${id}`)
        .then(res=>res.json())
        .then(d=>{
            if(d.invoices.length>0){
                document.querySelector(".invoice_id").value=d.invoices[0].invoiceCode
            }else{
                document.querySelector(".invoice_id").value=""
            }
            displayData(d)
        })
    }
})

function preventSubmit(event) {
    event.preventDefault();
    // Perform any additional actions or validations
}
const displayData=(tableData)=>{
    
    let orders="";
    isSelected=true
    let num=0;
    if(tableData.status.status=="Busy"){
        document.querySelector(".clear").classList.remove("hide")
    }else{
        document.querySelector(".clear").classList.add("hide")
    }
    tableData.invoices.forEach(item => {
        item.invoiceDetails.forEach(i=>{
            num++;
            orders+=
            `
                <div class="data">${i.product.name} (${i.size.size.short_name} x ${i.quantity})</div>
            `
        })

    });
    if(num==0) orders =`<div class="data">Not order yet</div>`
    const textHtml=
    `


                   <div class="number">
                    Table:&nbsp;&nbsp;
                    <div class="value">${tableData.id}</div>
                   </div>

                   <div class="status">
                    Status:
                    <div class="value">${tableData.status.status}</div>
                   </div>

                   <div class="order">
                    Orders:
                    <div class="value">
    ${orders}
                    </div>
                   </div>
       
                `
              document.querySelector(".content").innerHTML=textHtml
}

const alertError=()=>{
    Swal.fire({
        title: 'Warning',
        text: 'Please Choose table',
        imageUrl: '/images/icons/warning.png',
        imageHeight: 200,
        imageAlt: 'Custom image',
      })
}
document.querySelector(".next").addEventListener("click",(e)=>{
    if(!isSelected){
        e.preventDefault()
        alertError()
    }
})

document.querySelector(".clear").addEventListener("click",(e)=>{
    e.preventDefault()
    const form =document.createElement("form")
    form.action="/v1/tables/free/"+tableId
    form.method="POST"
    document.querySelector("body").appendChild(form)
    form.submit();
    form.remove()
})