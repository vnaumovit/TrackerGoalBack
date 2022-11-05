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
    console.log(quantity)
    if (id != null) {
      sizes.push({ id: id, size: size, quantity: quantity })
    } else {
      sizes.push({ size: size, quantity: quantity })
    }
  }
  return sizes;
}

async function addOrDeleteSize(modal, count) {
  let currentCount = count
  $('#addElseSize').click(async () => {
    currentCount = await addSizeForm(modal, currentCount);
    $("#sizeCount").val(currentCount)
  })
  $('#deleteSizeButton').click(async () => {
    currentCount = await deleteSizeForm(currentCount);
    $("#sizeCount").val(currentCount)
  });
  return currentCount;
}

function addSizeBaseForm(modal) {
  let baseForm = `
      <form class="form-group text-center needs-validation" id="addSizeForm" novalidate>
      </form>
      <input id="sizeCount" hidden>`;
  modal.find('.modal-body-size').append(baseForm);
}

async function deleteSizeForm(count) {
  let currCount = count
  if (count > 0) {
    let parent = document.getElementById('addSizeForm');
    let child = document.getElementById('sizeForm' + count);
    parent.removeChild(child)
    currCount--;
  }
  return currCount;
}

async function addSizeForm(modal, count) {
  let currCount = count
  if (count <= 5) {
    currCount++;
    let bodyForm = `
              <div class="form-floating mb-3" id="sizeForm${currCount}">
                  <label for="size${currCount}">Размер ${currCount}</label>
                  <input class="form-control" id="size${currCount}" required min="0">
                  <label for="quantity${currCount}">Количество ${currCount} размера</label>
                  <input class="form-control" id="quantity${currCount}" required min="0">
                  <div class="invalid-feedback">Добавьте размеры и количество</div>
                  <div class="valid-feedback">Все хорошо!</div>
              </div>
        `;
    let form = document.querySelector('#addSizeForm');
    modal.find(form).append(bodyForm);
  }
  return currCount;
}