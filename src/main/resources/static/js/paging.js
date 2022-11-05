function setPaging(number) {
  pagingNumber = number;
  getItems();
  checkSelectButtonPaging();
}

function pagingPrev() {
  if (pagingNumber > 0) {
    pagingNumber--;
    getItems();
    checkSelectButtonPaging();
  }
}

function pagingNext() {
  if (pagingNumber >= 0) {
    pagingNumber++;
    getItems();
    checkSelectButtonPaging();
    $('#bt1').removeClass('disabled')
  }
}


function checkSelectButtonPaging() {
  if (pagingNumber === 0) {
    $('#bt4').removeClass('active');
    $('#bt3').removeClass('active');
    $('#bt2').addClass('active');
    $('#bt1').addClass('disabled')
  } else if (pagingNumber === 1) {
    $('#bt3').addClass('active');
    $('#bt2').removeClass('active');
    $('#bt4').removeClass('active');
    $('#bt1').removeClass('disabled')
  } else if (pagingNumber === 2) {
    $('#bt4').addClass('active');
    $('#bt2').removeClass('active');
    $('#bt3').removeClass('active');
    $('#bt1').removeClass('disabled')
  }
}

