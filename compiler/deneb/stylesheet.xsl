<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:lst="http://deneblingvo.org/xsd/transformator/list/1.0"
	xmlns:dst="http://deneblingvo.org/xsd/transformator/destination/1.0"
	xmlns:den="http://deneblingvo.org/xsd/notation/1.0" 
	xmlns:lib="http://deneblingvo.org/xsd/deneb/library/1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	exclude-result-prefixes="#all">

	<xsl:output method="xml" 
	            version="1.0"
	            indent="yes"
	            encoding="UTF-8"
	            undeclare-prefixes="no" />

	<xsl:namespace-alias result-prefix="#default" stylesheet-prefix="#default"/>

	<xsl:template match="/">
		<xsl:element name="dst:destination"
		             namespace="http://deneblingvo.org/xsd/transformator/destination/1.0">
			<xsl:namespace name="dst">http://deneblingvo.org/xsd/transformator/destination/1.0</xsl:namespace>
			<xsl:element name="dst:file"
			             namespace="http://deneblingvo.org/xsd/transformator/destination/1.0">
				<xsl:element name="lib:library"
				             namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
<!--
					<xsl:for-each select="lst:list/den:notation/den:definition">
						<lib:class>
							<xsl:attribute name="name">
								<xsl:value-of select="@lexeme" />
							</xsl:attribute>
						</lib:class>
					</xsl:for-each>
-->
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>
