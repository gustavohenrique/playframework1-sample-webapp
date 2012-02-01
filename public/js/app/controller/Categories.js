Ext.define('PoupaNiquel.controller.Categories', {
	extend        : 'PoupaNiquel.controller.Common',
    myModelName   : 'Category',
    myStoreName   : 'Categories',
    myPanelName   : 'categoriesPanel',
    myGridSelector: 'categoriesGrid',
    myTitle       : 'Categories',
    
    init: function() {
    	this.control({
    		'#categoriesGrid button[action=add]': {click: this.add},
    		'#categoriesGrid button[action=delete]': {click: this.delete},
    	});
    },
});