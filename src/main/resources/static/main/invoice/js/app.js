const menu = document.querySelector('.menu');
const arrowLeft = document.querySelector('.arrow_left');
const arrowRight = document.querySelector('.arrow_right');

const scrollStep = 100; // Adjust the scroll step as per your requirement

let scrollingInterval;
let touchStartX = 0;
let touchStartY = 0;
let screenText="";
let isHasDot=false;
const scrollLeft = () => {
  menu.scrollLeft -= scrollStep;
};

const scrollRight = () => {
  menu.scrollLeft += scrollStep;
};




const handleTouchEnd = () => {
  touchStartX = 0;
  touchStartY = 0;
};

arrowLeft.addEventListener('click', scrollLeft);
arrowRight.addEventListener('click', scrollRight);

arrowLeft.addEventListener('mousedown', () => {
  scrollingInterval = setInterval(scrollLeft, 100);
});

arrowRight.addEventListener('mousedown', () => {
  scrollingInterval = setInterval(scrollRight, 100);
});

const stopScrolling = () => {
  clearInterval(scrollingInterval);
};

arrowLeft.addEventListener('mouseup', stopScrolling);
arrowRight.addEventListener('mouseup', stopScrolling);
document.addEventListener('mouseleave', stopScrolling);


document.querySelector(".table_invoice_infor").addEventListener("click",async (e)=>{
  const target=e.target;
  if(target.classList.contains('see')){

    document.querySelector(".popup_container").innerHTML=`<div class="lds-dual-ring"></div>`
    let invoice_detail_id=target.getAttribute("data-detail-id")
    let qty=target.getAttribute("data-qty")
    let invoiceId=document.querySelector(".popup").getAttribute("data-invoiceid")
    let productId=target.getAttribute("data-id")
    let productSizeFetch=fetch(`/v1/drinkFoodSize/${productId}`);
    let productToppingFetch=fetch(`/v1/drinkFoodTopping/${productId}`);
    let zoneFetch=fetch(`/v1/zone`);
    let toppingText="";
    let sizeText="";
    let zoneText="";
    let text="";
    let ProductName=target.getAttribute("data-name");
    
    let tmpRes = await fetch(`/v1/invoiceDetail/${invoice_detail_id}`)
    let invoice_detail= await tmpRes.json();

    Promise.all([productToppingFetch,productSizeFetch,zoneFetch])
    .then(async res=>{
      let topping=await res[0].json()
      let size=await res[1].json()
      let zone=await res[2].json()


      size.forEach(item=>{
        let tmptext;
        console.log(item,invoice_detail.sizeId,item.sizeId==invoice_detail.sizeId);
        if(item.id==invoice_detail.sizeId){
           tmptext=`
          <div class="size_con">
          `
          tmptext+=
          `
          <input type="radio" name="sizeId" checked id="${item.size.short_name}" value="${item.id}">
           <label for="${item.size.short_name}" class="size active">${item.size.short_name}</label>
          `
          tmptext+=
          `
          </div>
          `

        }else{
           tmptext=`
          <div class="size_con">
          `
          tmptext+=
          `
          <input type="radio" name="sizeId" id="${item.size.short_name}" value="${item.id}">
           <label for="${item.size.short_name}" class="size">${item.size.short_name}</label>
          `
          tmptext+=
          `
          </div>
          `
        }

        sizeText+=tmptext
      })



      topping.forEach(item=>{
        if(item.id==invoice_detail.toppingId){
          toppingText+=`<option value="${item.id}" selected>${item.topping.name}</option>\n`
          
        }else{
          toppingText+=`<option value="${item.id}">${item.topping.name}</option>\n`

        }
      })
      
      zone.forEach(item=>{
        zoneText+=`<option value="${item.id}">${item.name}</option>\n`
      })

      text=
      `
      <h2>${ProductName}</h2>
      <input type="hidden" name="id" value="${invoice_detail_id}">
      <input type="hidden" name="invoiceId" value="${invoiceId}">
      <input type="hidden" name="productId" value="${productId}">
      <div class="cover" data-label="Size">
            ${sizeText}
      </div>
      <div class="flex con" data-label="">
          <div class="cover flex" data-label="Quantity">
            <input type="number" name="quantity" min="1" step="1" value="${invoice_detail.quantity}" placeholder="Quantity" class="input_quantity">
          </div>
          <div class="cover flex" data-label="Discount">
          <input type="number" name="discount" min="0" step="0.1" value="${invoice_detail.discount}" placeholder="Discount" class="input_quantity">
          </div>
      </div>
      <div class="cover flex" data-label="Options">
        <div class="option">
          <div class="label">Cream</div>
          <div class="input">
              <select name="toppingId">
              <option value="-1">--no-topping--</option>
      ${toppingText}
                </select>
              

          </div>
        </div>
        <div class="option">
          <div class="label">Sugar</div>
          <div class="input">
              <select name="surgarRate">
                  <option value="0">0%</option>
                  <option value="10">10%</option>
                  <option value="20">20%</option>
                  <option value="30">30%</option>
                  <option value="40">40%</option>
                  <option value="50">50%</option>
                  <option value="60">60%</option>
                  <option value="70">70%</option>
                  <option value="80">80%</option>
                  <option value="90">90%</option>
                  <option value="100" selected>100%</option>
                  <option value="110">110%</option>
                  <option value="120">120%</option>
                  <option value="130">130%</option>
                  <option value="140">140%</option>
                  <option value="150">150%</option>
                  <option value="160">160%</option>
                  <option value="170">170%</option>
                  <option value="180">180%</option>
                  <option value="190">190%</option>
                  <option value="200">200%</option>
                </select>
          </div>
        </div>
        <div class="option">
          <div class="label">Zone</div>
          <div class="input">
              <select name="zoneId">
      ${zoneText}
                  
                </select>
          </div>
        </div>
      </div>  


      `
      document.querySelector(".popup_container").innerHTML=text
    })
    document.querySelector(".edit_invoice_detail").innerText="Save"
    document.querySelector('.popup').style.display="flex"
  }

  if(target.classList.contains("trash")){
    let invoice_detail_id=target.getAttribute("data-detail-id")
    let invoiceCode=target.getAttribute("data-invoicecode")
    let categoryCode=target.getAttribute("data-categorycode")
    const form=document.createElement("form")
    form.method="POST"
    form.action=`/v1/invoiceDetail/delete/${invoice_detail_id}`

    var invoiceCodeInput = document.createElement("input");
    invoiceCodeInput.type = "hidden";
    invoiceCodeInput.name = "invoiceCode";
    invoiceCodeInput.value = invoiceCode;

    var categoryCodeInput = document.createElement("input");
    categoryCodeInput.type = "hidden";
    categoryCodeInput.name = "categoryCode";
    categoryCodeInput.value = categoryCode;

    form.appendChild(categoryCodeInput);
    form.appendChild(invoiceCodeInput);

    form.style.display="none"
    document.querySelector("body").appendChild(form)
    form.submit()
    form.remove();
  }
})

document.querySelector('.menu_item').addEventListener("click",(e)=>{
  const target=e.target;
  // console.dir(target);
  if(target.classList.contains('product')){

    document.querySelector(".popup_container").innerHTML=`<div class="lds-dual-ring"></div>`

    let productId=target.getAttribute("data-id")
    let productSizeFetch=fetch(`/v1/drinkFoodSize/${productId}`);
    let invoiceId=document.querySelector(".popup").getAttribute("data-invoiceid")
    let productToppingFetch=fetch(`/v1/drinkFoodTopping/${productId}`);
    let zoneFetch=fetch(`/v1/zone`);
    let toppingText="";
    let sizeText="";
    let zoneText="";
    let text="";
    let ProductName=target.getAttribute("data-name");
    Promise.all([productToppingFetch,productSizeFetch,zoneFetch])
    .then(async res=>{
      let topping=await res[0].json()
      let size=await res[1].json()
      let zone=await res[2].json()

      let i=0;
      size.forEach(item=>{
        let checked=i>0 ?"":"checked"
        let active=i>0 ?"":"active"
        let tmptext=`
        <div class="size_con">
        `
        tmptext+=
        `
        <input type="radio" name="sizeId" ${checked} id="${item.size.short_name}" value="${item.id}">
         <label for="${item.size.short_name}" class="size ${active}">${item.size.short_name}</label>
        `
        tmptext+=
        `
        </div>
        `
        i++
        sizeText+=tmptext
      })



      topping.forEach(item=>{
        toppingText+=`<option value="${item.id}">${item.topping.name}</option>\n`
      })
      
      zone.forEach(item=>{
        zoneText+=`<option value="${item.id}">${item.name}</option>\n`
      })

      text=
      `
      <h2>${ProductName}</h2>
      <input type="hidden" name="invoiceId" value="${invoiceId}">
      <input type="hidden" name="productId" value="${productId}">
      <div class="cover" data-label="Size">
            ${sizeText}
      </div>
      <div class="flex con" data-label="">
          <div class="cover flex" data-label="Quantity">
            <input type="number" name="quantity" min="1" step="1" value="1" placeholder="Quantity" class="input_quantity">
          </div>
          <div class="cover flex" data-label="Discount">
          <input type="number" name="discount" min="0" step="0.1" value="0" placeholder="Discount" class="input_quantity">
          </div>
      </div>
      <div class="cover flex" data-label="Options">
        <div class="option">
          <div class="label">Cream</div>
          <div class="input">
              <select name="toppingId">
              <option value="-1" selected>--no-topping--</option>
              ${toppingText}
              </select>
              

          </div>
        </div>
        <div class="option">
          <div class="label">Sugar</div>
          <div class="input">
              <select name="surgarRate">
                  <option value="0">0%</option>
                  <option value="10">10%</option>
                  <option value="20">20%</option>
                  <option value="30">30%</option>
                  <option value="40">40%</option>
                  <option value="50">50%</option>
                  <option value="60">60%</option>
                  <option value="70">70%</option>
                  <option value="80">80%</option>
                  <option value="90">90%</option>
                  <option value="100" selected>100%</option>
                  <option value="110">110%</option>
                  <option value="120">120%</option>
                  <option value="130">130%</option>
                  <option value="140">140%</option>
                  <option value="150">150%</option>
                  <option value="160">160%</option>
                  <option value="170">170%</option>
                  <option value="180">180%</option>
                  <option value="190">190%</option>
                  <option value="200">200%</option>
                </select>
          </div>
        </div>
        <div class="option">
          <div class="label">Zone</div>
          <div class="input">
              <select name="zoneId">
      ${zoneText}
                  
                </select>
          </div>
        </div>
      </div>  


      `
      document.querySelector(".popup_container").innerHTML=text
    })
    document.querySelector(".edit_invoice_detail").innerText="Add"
    document.querySelector('.popup').style.display="flex"
  }
})
document.querySelector('.popup').addEventListener('click',(e)=>{
  if(e.target.classList.contains('popup')){
    document.querySelector('.popup').style.display="none"
  }
})

document.querySelector(".popup_container").addEventListener("click",(e)=>{
  const target=e.target;
  if(target.classList.contains("size")){
    document.querySelectorAll(".size").forEach(item=>{item.classList.remove("active")})
    target.classList.add("active")
  }
  
})

document.querySelector('.cal_btn').addEventListener('click',(e)=>{
  e.preventDefault()
  const target=e.target;
  if(target.hasAttribute('data-number')){
    if(screenText=='00.00'){
      screenText=''
    }
    if(target.getAttribute('data-number')=='.'){
      if(!isHasDot && screenText.length>0){
        screenText=`${screenText}${target.getAttribute('data-number')}`
        isHasDot=true;
        displayNumber(screenText)
      }
    }else{
      screenText=`${screenText}${target.getAttribute('data-number')}`
      displayNumber(screenText)
    }
  }
})

document.querySelector('[data-delete]').addEventListener('click',(e)=>{
  e.preventDefault()
  if(screenText=='' || screenText=='00.00' ){
    screenText='00.00'
    return
  }
  
  // console.log();
  screenText=screenText.slice(0, -1)
  if(screenText=='') screenText='00.00'
  displayNumber(screenText)
})

document.querySelector('[data-cancel]').addEventListener('click',(e)=>{
  e.preventDefault()
  calculatorOff()
})


const displayNumber=(Text)=>{
  // e.preventDefault()
  document.querySelector('[data-screen]').innerText=Text;
  if(Text!='00.00'){
    document.querySelector('[data-change]').setAttribute("data-change",eval(`${Text}-${document.querySelector('[data-total]').getAttribute('data-total')}`).toFixed(2))
    document.querySelector('[data-change]').innerText=eval(`${Text}-${document.querySelector('[data-total]').getAttribute('data-total')}`).toFixed(2);

  }else{
    document.querySelector('[data-change]').innerText='00.00'
  }
}


const keyEvent=()=>{
  window.addEventListener('keydown',(e)=>{
    // console.log(e.key);
    const keyEle=document.querySelector(`[data-${getKeyName(e.key)}]`)
    if(keyEle){
      keyEle.click()
    }
  })
}

const getKeyName=(keyName)=>{
  if(keyName=='.'){
    return 'dot'
  }else if(keyName=='Backspace'){
    
    return 'delete'
    
  }else if(keyName=='Delete'){
    
    return 'delete'
    
  }else if(keyName=='Enter'){
    return 'ok'
  }else if(keyName=='Escape'){
    return 'cacel'
  }
  return keyName
}
keyEvent()

document.querySelector('[data-receipt]').addEventListener('click',()=>{
    calculatorShow()
})

document.querySelector('.popup_calculator').addEventListener('click',(e)=>{
  // console.log(e.target);
  if(e.target.classList.contains('popup_calculator')){
    calculatorOff()
  }
})

const calculatorShow=()=>{
  document.querySelector('.popup_calculator').style.display='flex'
}
const calculatorOff=()=>{
  document.querySelector('[data-screen]').innerText='00.00'
  document.querySelector('[data-change]').innerText='00.00'
  document.querySelector('.popup_calculator').style.display='none'
  screenText='00.00'
  
}

document.querySelector(".cancel").addEventListener("click",(e)=>{
  e.preventDefault()
  document.querySelector('.popup').style.display="none"
})

document.querySelector(".ok").addEventListener("click",(e)=>{
  e.preventDefault()
  let moneyChange=document.querySelector(".moneyChange")
  let receiveMoney=document.querySelector(".receiveMoney")

  if(moneyChange.getAttribute("data-change")<=0) return 

  const form=document.createElement("form")
  form.method="POST"
  form.action=`/v1/receipt/new`

  var moneyChangeInput = document.createElement("input");
  moneyChangeInput.type = "hidden";
  moneyChangeInput.name = "changeMoney";
  moneyChangeInput.value = moneyChange.getAttribute("data-change");

  var receiveMoneyInput = document.createElement("input");
  receiveMoneyInput.type = "hidden";
  receiveMoneyInput.name = "receiveMoney";
  receiveMoneyInput.value = receiveMoney.innerText;

  var receiptIdInput = document.createElement("input");
  receiptIdInput.type = "hidden";
  receiptIdInput.name = "receiptId";
  receiptIdInput.value = e.target.getAttribute("data-receiptid") | "-1";

  var invoiceIdInput = document.createElement("input");
  invoiceIdInput.type = "hidden";
  invoiceIdInput.name = "invoiceId";
  invoiceIdInput.value = e.target.getAttribute("data-invoiceid") ;

  form.appendChild(moneyChangeInput);
  form.appendChild(receiveMoneyInput);
  form.appendChild(receiptIdInput);
  form.appendChild(invoiceIdInput);

  form.style.display="none"
  document.querySelector("body").appendChild(form)
  form.submit()
  form.remove()

})