const btns=document.querySelector(".left")
let data;
btns.addEventListener('click',(e)=>{
    const target=e.target;
    if(target.classList.contains('btn')){
        const id=target.getAttribute("data-id")
        fetch(`/v1/tables/${id}`)
        .then(res=>res.json())
        .then(d=>{
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
    let num=0;
    if(tableData.status.status=="Busy"){
        document.querySelector(".clear").classList.remove("hide")
        document.querySelector(".paid").classList.add("hide")
    }else if(tableData.status.status=="Unpaid") {
        document.querySelector(".clear").classList.add("hide")
        document.querySelector(".paid").classList.remove("hide")
    }else{
        document.querySelector(".clear").classList.add("hide")
        document.querySelector(".paid").classList.add("hide")
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