Ext.Loader.setConfig({enabled:true});

//Ext.application
Ext.create('Ext.app.Application', {
    name: 'PoupaNiquel',    
    autoCreateViewport: true,
    models: ['Transaction'],
    //controllers: ['Transactions'],
    
    launch: function() {
        alert('sdfsdfsfs');
    }
});