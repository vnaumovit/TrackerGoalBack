async function sizesData(modal, itemId, count) {
  let id = null;
  let size = null;
  let quantity = null;
  for (let i = 1; i <= count; i++) {
    if (itemId != null) {
      let idSize = modal.find('#id' + i);
      if (idSize !== undefined) {
        id = idSize.val();
      }
    }
    size = modal.find('#size' + i).val();
    quantity = modal.find('#quantity' + i).val();
    if (id != null) {
      sizes.push({ id: id, size: size, quantity: quantity })
    } else {
      sizes.push({ size: size, quantity: quantity })
    }
  }
  return filterSizes(sizes);
}

async function sizesForeach(sizes, count) {
  let currentCount = count;
  let sizesForm = ''
  await sizes.forEach(s => {
    currentCount++;
    let size = `size${currentCount}`
    let quantity = `quantity${currentCount}`
    sizesForm += `
        <div class="form-floating mb-3" id="sizeForm${currentCount}">
            <input hidden id="id${currentCount}" value="${s.id}">
            <label class="col-form-label">Размер ${currentCount}</label>
            <input class="form-control" id="${size}" required min="0" value="${s.size}">
            <label class="col-form-label">Количество ${currentCount}</label>
            <input class="form-control" id="${quantity}" required min="0" value="${s.quantity}">
            <div class="invalid-feedback">Добавьте размеры</div>
            <div class="valid-feedback">Все хорошо!</div>
        </div>`
  })
  $('#sizeCount').val(currentCount)
  return sizesForm;
}
