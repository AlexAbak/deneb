<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:lst="http://deneblingvo.org/xsd/transformator/list/1.0"
	xmlns:dst="http://deneblingvo.org/xsd/transformator/destination/1.0"
	xmlns:den="http://deneblingvo.org/xsd/notation/1.0" xmlns:lib="http://deneblingvo.org/xsd/deneb/library/1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:output method="xml" version="1.0" indent="yes"
		encoding="UTF-8" />

	<xsl:template match="/">
		<dst:destination>
			<dst:file>
				<lib:library>
					<xsl:for-each select="lst:list/den:notation/den:definition">
						<lib:class>
							<xsl:attribute name="name">
								<xsl:value-of select="@lexeme" />
							</xsl:attribute>
						</lib:class>
					</xsl:for-each>
				</lib:library>
			</dst:file>
		</dst:destination>
	</xsl:template>

</xsl:stylesheet>
