$('.all-sort-list > .item').hover(function () {
    var eq = $('.all-sort-list > .item').index(this),
        h = $('.all-sort-list').offset().top,
        s = $(window).scrollTop(),
        i = $(this).offset().top,
        item = $(this).children('.subitem').height(),
        sort = $('.all-sort-list').height();

    if (item < sort) {
        if (eq == 0) {
            $(this).children('.subitem').css('top', (i - h));
        } else {
            $(this).children('.subitem').css('top', (i - h) + 1);
        }
    } else {
        if (s > h) {
            if (i - s > 0) {
                $(this).children('.subitem').css('top', (s - h) + 2);
            } else {
                $(this).children('.subitem').css('top', (s - h) - (-(i - s)) + 2);
            }
        } else {
            $(this).children('.subitem').css('top', 3);
        }
    }

    $(this).addClass('hover');
    $(this).children('.subitem').css('display', 'block');
}, function () {
    $(this).removeClass('hover');
    $(this).children('.subitem').css('display', 'none');
});


var slideshowSwiper = new Swiper('.swiper-container', {
    autoplay: {
        delay: 2000,
        disableOnInteraction: false
    },
    loop: true,
    pagination: {
        el: '.swiper-pagination',
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
})

const InputBinding = {
    data() {
        return {
            search_content: ''
        }
    }
}
const vm = Vue.createApp(InputBinding).mount('#search-content-binding');

function searchMerchandise() {
    window.location.href = "/mall/search?title=" + vm.search_content;
}