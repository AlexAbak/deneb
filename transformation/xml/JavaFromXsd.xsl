<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	 <xsl:output method="xml" encoding="UTF-8" indent="yes" /> 
	<xsl:template match="/">
		<xsl:element name="root">
			<xsl:copy-of select="//xs:schema"/> 
			<xsl:copy-of select="//set"/>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
