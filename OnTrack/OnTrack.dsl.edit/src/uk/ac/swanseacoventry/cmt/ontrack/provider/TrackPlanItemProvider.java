/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.TrackPlan;

/**
 * This is the item provider adapter for a {@link uk.ac.swanseacoventry.cmt.ontrack.TrackPlan} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TrackPlanItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackPlanItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSelectedSubTrackPlanPropertyDescriptor(object);
			addOverlappedPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Selected Sub Track Plan feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSelectedSubTrackPlanPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TrackPlan_selectedSubTrackPlan_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TrackPlan_selectedSubTrackPlan_feature", "_UI_TrackPlan_type"),
				 OntrackPackage.Literals.TRACK_PLAN__SELECTED_SUB_TRACK_PLAN,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Overlapped feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOverlappedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TrackPlan_overlapped_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TrackPlan_overlapped_feature", "_UI_TrackPlan_type"),
				 OntrackPackage.Literals.TRACK_PLAN__OVERLAPPED,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__TRACKS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__CONNECTORS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__POINTS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__CROSSINGS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__SIGNALS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__ENTRANCES);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__EXITS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__TERMINALS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__TOPO_ROUTES);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__CONTROL_TABLE);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__RELEASE_TABLE);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__NEW_TRACK);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__NEW_POINT);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__NEW_CROSSING);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__SUB_TRACK_PLANS);
			childrenFeatures.add(OntrackPackage.Literals.TRACK_PLAN__SIMULATIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns TrackPlan.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TrackPlan"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		TrackPlan trackPlan = (TrackPlan)object;
		return getString("_UI_TrackPlan_type") + " " + trackPlan.isOverlapped();
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TrackPlan.class)) {
			case OntrackPackage.TRACK_PLAN__OVERLAPPED:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case OntrackPackage.TRACK_PLAN__TRACKS:
			case OntrackPackage.TRACK_PLAN__CONNECTORS:
			case OntrackPackage.TRACK_PLAN__POINTS:
			case OntrackPackage.TRACK_PLAN__CROSSINGS:
			case OntrackPackage.TRACK_PLAN__SIGNALS:
			case OntrackPackage.TRACK_PLAN__ENTRANCES:
			case OntrackPackage.TRACK_PLAN__EXITS:
			case OntrackPackage.TRACK_PLAN__TERMINALS:
			case OntrackPackage.TRACK_PLAN__TOPO_ROUTES:
			case OntrackPackage.TRACK_PLAN__CONTROL_TABLE:
			case OntrackPackage.TRACK_PLAN__RELEASE_TABLE:
			case OntrackPackage.TRACK_PLAN__NEW_TRACK:
			case OntrackPackage.TRACK_PLAN__NEW_POINT:
			case OntrackPackage.TRACK_PLAN__NEW_CROSSING:
			case OntrackPackage.TRACK_PLAN__SUB_TRACK_PLANS:
			case OntrackPackage.TRACK_PLAN__SIMULATIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__TRACKS,
				 OntrackFactory.eINSTANCE.createTrack()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__CONNECTORS,
				 OntrackFactory.eINSTANCE.createConnector()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__POINTS,
				 OntrackFactory.eINSTANCE.createPoint()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__CROSSINGS,
				 OntrackFactory.eINSTANCE.createCrossing()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__SIGNALS,
				 OntrackFactory.eINSTANCE.createSignal()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__ENTRANCES,
				 OntrackFactory.eINSTANCE.createEntrance()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__EXITS,
				 OntrackFactory.eINSTANCE.createExit()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__TERMINALS,
				 OntrackFactory.eINSTANCE.createTerminal()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__TOPO_ROUTES,
				 OntrackFactory.eINSTANCE.createTopoRoute()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__CONTROL_TABLE,
				 OntrackFactory.eINSTANCE.createControlTableItem()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__RELEASE_TABLE,
				 OntrackFactory.eINSTANCE.createReleaseTableItem()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__NEW_TRACK,
				 OntrackFactory.eINSTANCE.createNewTrack()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__NEW_POINT,
				 OntrackFactory.eINSTANCE.createNewPoint()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__NEW_CROSSING,
				 OntrackFactory.eINSTANCE.createNewCrossing()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__SUB_TRACK_PLANS,
				 OntrackFactory.eINSTANCE.createSubTrackPlan()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.TRACK_PLAN__SIMULATIONS,
				 OntrackFactory.eINSTANCE.createSimulation()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return OntrackEditPlugin.INSTANCE;
	}

}
