Ext.Loader.setConfig({enabled:true});

Ext.application({
    name: 'PoupaNiquel',
    appFolder: '/public/js/app',
    autoCreateViewport: false,

    controllers: ['Main', 'Transactions', 'Accounts'],
    
    launch: function() {
    	
        viewport = Ext.create('Ext.container.Viewport', {
        	id: 'viewport',
            layout: 'border',
            items: [{
            	id: 'viewportCenter',
                region: 'center',
                autoScroll: true,
                border: false,
                autoScroll: true,

            }, {
            	region: 'north',
            	height: 45,
            	xtype: 'mainTop',
            }]
        });
    }
});