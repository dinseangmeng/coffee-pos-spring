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

document.querySelector('.menu_item').addEventListener("click",(e)=>{
  const target=e.target;
  // console.dir(target);
  if(target.classList.contains('product')){

    document.querySelector(".popup_container").innerHTML=`<div class="lds-dual-ring"></div>`

    let productId=target.getAttribute("data-id")
    let productSizeFetch=fetch(`/v1/drinkFoodSize/${productId}`);
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


      size.forEach(item=>{
        let tmptext=`
        <div class="size_con">
        `
        tmptext+=
        `
        <input type="radio" name="size" id="${item.size.short_name}">
         <label for="${item.size.short_name}" class="size">${item.size.short_name}</label>
        `
        tmptext+=
        `
        </div>
        `
        sizeText+=tmptext
      })



      topping.forEach(item=>{
        toppingText+=`<option>${item.topping.name}</option>\n`
      })
      
      zone.forEach(item=>{
        zoneText+=`<option>${item.name}</option>\n`
      })

      text=
      `
      <h2>${ProductName}</h2>
      <div class="cover" data-label="Size">
            ${sizeText}
      </div>
      <div class="cover flex" data-label="Options">
        <div class="option">
          <div class="label">Cream</div>
          <div class="input">
              <select>
      ${toppingText}
                </select>
              

          </div>
        </div>
        <div class="option">
          <div class="label">Sugar</div>
          <div class="input">
              <select>
                  <option>0%</option>
                  <option>10%</option>
                  <option>20%</option>
                  <option>30%</option>
                  <option>40%</option>
                  <option>50%</option>
                  <option>60%</option>
                  <option>70%</option>
                  <option>80%</option>
                  <option>90%</option>
                  <option>100%</option>
                </select>
          </div>
        </div>
        <div class="option">
          <div class="label">Zone</div>
          <div class="input">
              <select>
      ${zoneText}
                  
                </select>
          </div>
        </div>
      </div>  


      `
      document.querySelector(".popup_container").innerHTML=text
    })
   
    document.querySelector('.popup').style.display="flex"
  }
})
document.querySelector('.popup').addEventListener('click',(e)=>{
  if(e.target.classList.contains('popup')){
    document.querySelector('.popup').style.display="none"
  }
})


document.querySelector('.cal_btn').addEventListener('click',(e)=>{
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
  if(screenText=='' || screenText=='00.00' ){
    screenText='00.00'
    return
  }
  
  // console.log();
  screenText=screenText.slice(0, -1)
  if(screenText=='') screenText='00.00'
  displayNumber(screenText)
})

document.querySelector('[data-cancel]').addEventListener('click',()=>{
  calculatorOff()
})


const displayNumber=(Text)=>{
  document.querySelector('[data-screen]').innerText=Text;
  if(Text!='00.00'){
    document.querySelector('[data-change]').innerText=eval(`${Text}-${document.querySelector('[data-total]').getAttribute('data-total')}`)

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

document.querySelector('[data-receipt').addEventListener('click',()=>{
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