function filterSizes(sizes) {
  let resArr = [];
  sizes.filter(function (size) {
    let i = resArr.findIndex(x => x.size === size.size);
    if (i <= -1) {
      resArr.push({ id: size.id, size: size.size, quantity: size.quantity });
    }
    return null;
  });
  return resArr;
}

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

async function addOrDeleteSize(modal) {
  let currentCount = $('#sizeCount').val()
  $('#addElseSize').click(async () => {
    currentCount = await addSizeForm(modal, currentCount);
    $("#sizeCount").val(currentCount)
  })
  $('#deleteSizeButton').click(async () => {
    currentCount = await deleteSizeForm(currentCount);
    $("#sizeCount").val(currentCount)
  });
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
    if (sizes.length !== 0) {
      sizes.pop()
    }
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


async function fillOldSizes(modal) {
  let sizeCount = $('#sizeCount');
  let checkSizes = sizeCount.val() === '' && sizes.length === 0;
  if (checkSizes) {
    await addSizeForm(modal, 0);
    sizeCount.val(1)
  } else {
    let sizesForm = await sizesForeach(sizes, 0);
    let form = document.querySelector('#addSizeForm');
    modal.find(form).append(sizesForm);
  }
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
