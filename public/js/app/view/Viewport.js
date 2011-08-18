Ext.define('PoupaNiquel.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: [
        'PoupaNiquel.view.Transactions',
    ],
    layout: 'fit',
    initComponent: function() {
    	this.items = {
            xtype: 'panel',
            dockedItems: [{
                dock: 'top',
                xtype: 'toolbar',
                height: 80,
                items: [{
                    xtype: 'transactions',
                    width: 150
                }]
            }],
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [{
                width: 250,
                xtype: 'panel',
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                items: [{
                    xtype: 'stationslist',
                    flex: 1
                }, {
                    html: 'Ad',
                    height: 250,
                    xtype: 'panel'
                }]
            }, {
                xtype: 'container',
                flex: 1,
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                items: [{
                    xtype: 'transactions',
                    height: 250
                }]
            }]
        };
 
        this.callParent();	
    }
});