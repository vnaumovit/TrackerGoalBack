// convert image to byte array
async function base64(image) {
  return new Promise((resolve) => {
    const reader = new FileReader()
    reader.onloadend = () => resolve(atob(reader.result.split(',')[1]))
    reader.readAsDataURL(image)
  })
}

async function base64ToBinary(imageBase64) {
  let bytes = [];
  for (let i = 0; i < imageBase64.length; i++) {
    bytes.push(
      imageBase64.charCodeAt(i)
    );
  }
  return bytes
}