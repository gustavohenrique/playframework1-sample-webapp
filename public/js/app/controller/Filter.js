Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Filter', {
    extend: 'Ext.app.Controller',

    views: ['FilterPanel'],
    stores: ['Accounts'],
    models: ['Account'],
    
    init: function() {
    	this.control({
	      'button[id=btnLimpar]': {
	        click: this.teste
	      },
    	})
    },
    
    teste: function() {
    	alert('o botao foi clicado');
    }
});