Ext.Loader.setConfig({enabled:true});
Ext.application({
    name: 'PoupaNiquel',
    appFolder: '/public/js/app',
    autoCreateViewport: false,

    controllers: ['Top',],
    
    launch: function() {
        viewport = Ext.create('Ext.container.Viewport', {
        	requires: ['PoupaNiquel.view.TopPanel'],
            layout: 'border',
            items: [{
            	region: 'north',
            	height: 100,
				xtype:'toppanel',	
            }, {
                region: 'center',
                autoScroll: true,
                border: false,
                autoScroll: true,
            }, {
            	region: 'east',
				title: 'Filter',
				minWidth: 200,
				width: 250,
				split: true,
				border: false,
				frame: false,
            }]
        });
    }
});