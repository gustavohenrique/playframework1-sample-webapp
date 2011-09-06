Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Filter', {
    extend: 'Ext.app.Controller',

    views: ['FilterPanel'],
    stores: ['Accounts'],
    models: ['Account'],
    
    init: function() {
    },
});