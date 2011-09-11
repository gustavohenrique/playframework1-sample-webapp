Ext.Loader.setConfig({enabled:true});

Ext.application({
    name: 'PoupaNiquel',
    appFolder: '/public/js/app',
    autoCreateViewport: false,

    controllers: ['Main',],
    
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
                style: 'padding: 15px; background: white',
            }, {
            	region: 'north',
            	height: 73,
            	xtype: 'toppanel',
            }]
        });
    }
});