Ext.define('PoupaNiquel.view.common.MdiWindow', {
	extend: 'Ext.panel.Panel',
    alias: 'widget.mdiWindow',
	constrain: true,
    height: '100%',
    width: '100%',
    closable: true,
    closeAction: 'hide',
    plain: true,
    border: false,
    layout: 'fit',
    
    initComponent: function() {
    	return this.callParent(arguments);
    },
    
});    