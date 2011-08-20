// HTML5 placeholder plugin version 0.3
// Enables cross-browser* html5 placeholder for inputs, by first testing
// for a native implementation before building one.
//
// USAGE: 
//$('input[placeholder]').placeholder();

(function($){
  
  $.fn.placeholder = function(options) {
    return this.each(function() {
      if ( !("placeholder"  in document.createElement(this.tagName.toLowerCase()))) {
        var $this = $(this);
        var placeholder = $this.attr('placeholder');
        $this.val(placeholder);
        $this
          .focus(function(){ if ($.trim($this.val())==placeholder){ $this.val(''); }; })
          .blur(function(){ if (!$.trim($this.val())){ $this.val(placeholder); }; });
      }
    });
  };
})(jQuery);

// perform JavaScript after the document is scriptable.
$(document).ready(function() {
    $("ul.tabs").tabs("div.panes > div");
    $(".accordion").tabs(".accordion div.pane", {tabs: 'h2', effect: 'slide', initialIndex: null});

    $('.pricing-table article').hover(function() {
        $('.pricing-table article').removeClass('selected');
    });
    
    $('input[placeholder]').placeholder();

    $(":date").dateinput();
});