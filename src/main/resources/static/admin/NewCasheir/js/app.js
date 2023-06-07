document.querySelector(".passShow").addEventListener("click",(e)=>{
    document.querySelector("#password").type="text"
    document.querySelector(".passHide").classList.remove("hide")
    e.target.classList.add("hide")
})
document.querySelector(".passHide").addEventListener("click",(e)=>{
    document.querySelector("#password").type="password"
    document.querySelector(".passShow").classList.remove("hide")
    e.target.classList.add("hide")
})



function handleFileChange(event) {
    var file = event.target.files[0]; // Get the selected file
    var reader = new FileReader(); // Create a FileReader object

    reader.onload = function (event) {
      var imageUrl = event.target.result; // Get the data URL

      // Create a new image element
      var img = document.createElement('img');
      img.src = imageUrl;

      // Display the image in the preview element
      document.getElementById('preview').src = imageUrl;

      // Create a Blob URL from the file
      var blob = new Blob([file], { type: file.type });
      var blobUrl = URL.createObjectURL(blob);
      console.log("Blob URL:", blobUrl);
    };

    // Read the file as a data URL
    reader.readAsDataURL(file);
  }