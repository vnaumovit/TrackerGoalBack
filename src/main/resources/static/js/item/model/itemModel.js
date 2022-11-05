async function itemData(modal) {
  let image;
  let id;
  let imageFile = modal.find('#image')[0].files[0];
  if (modal.find('#id').val() !== undefined) {
    id = modal.find('#id').val();
    let imageRequest = await imageFetch.getImageByItemId(id);
    image = await imageRequest.json()
    if (sizes === null || sizes.length === 0) {
      let sizeRequest = await sizeFetch.getSizesByItemId(id);
      sizes = await sizeRequest.json();
    }
  }
  image = await editImage(imageFile, image, modal);
  let name = modal.find('#name').val();
  let code = modal.find('#code').val();
  console.log(name)
  if (name !== undefined) {
    name = name.trim();
  }
  let itemType = modal.find('#itemType').val();
  let retailPrice = modal.find('#retailPrice').val();
  return {
    id: id,
    name: name,
    code: code,
    itemType: itemType,
    retailPrice: retailPrice,
    sizes: sizes,
    image: image
  };
}

async function editImage(imageFile, image, modal) {
  let imageId
  if (imageFile !== undefined) {
    if (image !== undefined) {
      imageId = image.id;
    }
    image = await base64(imageFile)
    image = await base64ToBinary(image)
    let imageVal = modal.find('#image').val();
    image = {
      id: imageId,
      url: imageVal,
      name: imageFile.name,
      picture: image,
      isMain: true
    };
  }
  return image;
}